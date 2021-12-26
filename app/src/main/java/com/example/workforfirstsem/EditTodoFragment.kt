package com.example.workforfirstsem

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.workforfirstsem.databinding.FragmentEditTodoBinding
import com.example.workforfirstsem.model.AppDatabase
import com.example.workforfirstsem.model.entity.Todo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import java.util.*

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {

    private lateinit var db: AppDatabase

    private lateinit var binding: FragmentEditTodoBinding

    private val calendar = Calendar.getInstance()

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val permissionId = 42

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditTodoBinding.bind(view)

        db = AppDatabase(requireContext())

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        with(binding) {
            editTextDate.setOnClickListener {
                setDate()
            }
            btnDeleteDate.setOnClickListener {
                editTextDate.setText("")
            }
            setLongitudeAndLatitude()
            val id = arguments?.getInt("id")
            if (id != null) {
                val todo = db.todoDao().getById(id)
                etTitle.editText?.setText(todo.title)
                etDesc.editText?.setText(todo.desc)
                if (todo.date != null) {
                    setInitialDate(todo.date.time)
                }
                if (todo.latitude != "") {
                    etLatitude.editText?.setText(todo.latitude)
                }
                if (todo.longitude != "") {
                    etLongitude.editText?.setText(todo.longitude)
                }
                btnSave.setOnClickListener {
                    update(view, id)
                }
            } else {
                btnSave.setOnClickListener {
                    save(view)
                }
            }
        }
    }

    private fun setLongitudeAndLatitude() {
        if (checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            fusedLocationClient?.lastLocation?.addOnSuccessListener(
                requireActivity()
            ) { location: Location? ->
                if (location == null) {
                    Toast.makeText(
                        context,
                        getString(R.string.null_location),
                        Toast.LENGTH_LONG
                    ).show()
                } else location.apply {
                    with(binding) {
                        etLongitude.editText?.setText(location.longitude.toString())
                        etLatitude.editText?.setText(location.latitude.toString())
                    }
                }
            }
        }
    }

    private fun checkPermission(vararg perm: String): Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(requireContext(), it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if (perm.toList().any {
                    ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), it)
                }
            ) {
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.permission))
                    .setMessage(getString(R.string.needed_permission))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        ActivityCompat.requestPermissions(
                            requireActivity(), perm, permissionId
                        )
                    }
                    .setNegativeButton(getString(R.string.no)) { _, _ -> }
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(requireActivity(), perm, permissionId)
            }
            return false
        }
        return true
    }

    private fun save(view: View) {
        with(binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            val date = getDate()
            val latitude = etLatitude.editText?.text.toString()
            val longitude = etLongitude.editText?.text.toString()
            if (title.trim() == "" || desc.trim() == "") {
                Snackbar.make(
                    view,
                    getString(R.string.blank_title_desc),
                    LENGTH_LONG
                ).show()
            } else {
                db.todoDao().save(
                    Todo(
                        title,
                        desc,
                        date,
                        latitude,
                        longitude
                    )
                )
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    private fun getDate(): Date? {
        with(binding) {
            return if (editTextDate.text.toString() == "")
                null
            else
                Date(calendar.timeInMillis)
        }
    }

    private fun update(view: View, id: Int) {
        with(binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            val date = getDate()
            val latitude = etLatitude.editText?.text.toString()
            val longitude = etLongitude.editText?.text.toString()
            if (title.trim() == "" || desc.trim() == "") {
                Snackbar.make(
                    view,
                    getString(R.string.blank_title_desc),
                    LENGTH_LONG
                ).show()
            } else {
                db.todoDao().update(
                    Todo(id, title, desc, date, latitude, longitude)
                )
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    private fun setDate() {
        DatePickerDialog(
            requireContext(),
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        run {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDate(calendar.timeInMillis)
        }
    }

    private fun setInitialDate(timeInMillis: Long) {
        with(binding) {
            editTextDate.setText(
                DateUtils.formatDateTime(
                    requireContext(),
                    timeInMillis,
                    DateUtils.FORMAT_SHOW_DATE
                )
            )
        }
    }
}
