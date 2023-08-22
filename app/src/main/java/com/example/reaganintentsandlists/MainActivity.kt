package com.example.reaganintentsandlists

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.reaganintentsandlists.ui.theme.ReaganIntentsAndListsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaganIntentsAndListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Myintents()
                }
            }
        }
    }
}

@Composable
fun Myintents() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp, 20.dp, 20.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
var context = LocalContext.current
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                val uri = Uri.parse("smsto:0701780339")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("sms_body", "Aloooo")
                context.startActivity(intent) }) {
                Text(text = "SMS")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                val emailIntent =
                    Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
            }) {
                Text(text = "Email")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app from https://www.!")
                context.startActivity(shareIntent)
            }) {
                Text(text = "Share")
            }
            Spacer(modifier = Modifier.width(150.dp))
            Button(onClick = {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(context as Activity, takePictureIntent, 1, null) }) {
                Text(text = "Camera")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                val simToolKitLaunchIntent: Intent? = context.getPackageManager()
                    .getLaunchIntentForPackage("com.android.stk")
                if (simToolKitLaunchIntent != null) {
                    context.startActivity(simToolKitLaunchIntent)
                } }) {
                Text(text = "Mpesa")
            }
            Spacer(modifier = Modifier.width(150.dp))
            Button(onClick = {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"))
                if (ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        Activity(),
                        arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                        1
                    )
                } else {
                    context.startActivity(intent)
                }
            }) {
                Text(text = "Call")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Next page")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReaganIntentsAndListsTheme {
        Myintents()
    }
}