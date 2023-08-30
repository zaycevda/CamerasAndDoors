package com.example.camerasdoors.app.ui.screen.house.door

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.camerasdoors.R
import com.example.camerasdoors.app.ui.theme.Blue
import com.example.camerasdoors.app.ui.theme.CirceRegular
import com.example.camerasdoors.app.ui.theme.Grey100
import com.example.camerasdoors.app.ui.theme.Grey70
import com.example.camerasdoors.app.ui.theme.Grey80
import com.example.camerasdoors.app.ui.theme.Grey90

@Composable
fun EditDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .border(
                    width = 0.1.dp,
                    color = Grey90,
                    shape = RoundedCornerShape(size = 12.dp)
                ),
            shape = RoundedCornerShape(size = 12.dp),
            backgroundColor = Grey100
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.edit_title),
                    color = Grey70,
                    fontWeight = FontWeight(weight = 300),
                    fontSize = 21.sp,
                    fontFamily = CirceRegular,
                    textAlign = TextAlign.Start,
                    lineHeight = 30.95.sp
                )
                var text by remember { mutableStateOf(value = "") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.title),
                            color = Grey80,
                            fontSize = 17.sp,
                            fontWeight = FontWeight(weight = 400),
                            fontFamily = CirceRegular,
                            lineHeight = 25.06.sp
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { onConfirm(text) }),
                    shape = RoundedCornerShape(size = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = Blue,
                        unfocusedBorderColor = Color.White,
                        focusedBorderColor = Blue,
                        textColor = Grey80,
                        cursorColor = Blue
                    )
                )
            }
        }
    }
}