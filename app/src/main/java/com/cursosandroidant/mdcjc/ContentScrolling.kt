package com.cursosandroidant.mdcjc

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cursosandroidant.mdcjc.ui.theme.MDCJCTheme

/****
 * Project: MDC JC
 * From: com.cursosandroidant.mdcjc
 * Created by Alain Nicolás Tello on 08/02/23 at 11:14
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentPreview(){
    MDCJCTheme {
        Content()
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun Content(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            ConstraintLayout(modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default),
                    top = dimensionResource(id = R.dimen.common_padding_default))) {
                val (imgCard, btnBuy, btnSkip, tvTitle, tvContent) = createRefs()

                val image = ContextCompat.getDrawable(LocalContext.current, R.mipmap.ic_launcher)
                Image(bitmap = image!!.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(imgCard){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        })

                Button(onClick = { },
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.common_padding_min))
                        .constrainAs(btnBuy) {
                            end.linkTo(parent.end)
                            top.linkTo(imgCard.bottom)
                        }) {
                    Icon(painter = painterResource(id = R.drawable.ic_shop), contentDescription = null)
                    Text(text = stringResource(id = R.string.card_btn_buy))
                }

                TextButton(onClick = { },
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.common_padding_min))
                        .constrainAs(btnSkip){
                            end.linkTo(btnBuy.start)
                            top.linkTo(btnBuy.top)
                            bottom.linkTo(btnBuy.bottom)
                            height = Dimension.fillToConstraints
                        }) {
                    Text(text = stringResource(id = R.string.card_btn_skip))
                }

                Text(text = stringResource(id = R.string.card_title),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default))
                        .constrainAs(tvTitle) {
                            start.linkTo(imgCard.end)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        })

                Text(text = stringResource(id = R.string.large_text),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default))
                        .constrainAs(tvContent){
                            start.linkTo(tvTitle.start)
                            end.linkTo(tvTitle.end)
                            top.linkTo(tvTitle.bottom)
                            bottom.linkTo(imgCard.bottom)
                            width = Dimension.fillToConstraints
                        })
            }
        }

        var colorMain by remember{ mutableStateOf(Color.LightGray) }
        Card(modifier = Modifier
            .fillMaxWidth()
            .background(colorMain)
            .padding(8.dp)) {
            Column {
                /*Image(painter = painterResource(id = R.drawable.ic_shop),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)))*/
                var urlValue by remember { mutableStateOf("") }
                GlideImage(model = urlValue,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)),
                    contentScale = ContentScale.Crop)

                Text(text = stringResource(id = R.string.card_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.common_padding_default)),
                    style = MaterialTheme.typography.h5)

                OutlinedTextField(value = urlValue,
                    onValueChange = { urlValue = it },
                    label = { Text(text = stringResource(id = R.string.card_input_url))},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        ),
                    trailingIcon = {
                        if (urlValue.isNotEmpty()) {
                            Icon(imageVector = Icons.Filled.Clear,
                                contentDescription = "Limpiar",
                                modifier = Modifier
                                    .clickable { urlValue = "" })
                        }
                    })
                Text(text = stringResource(id = R.string.card_required),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default),
                            top = dimensionResource(id = R.dimen.common_padding_micro)))

                var isCheckboxChecked by remember{ mutableStateOf(false) }
                var passwordValue by remember{ mutableStateOf("") }
                var isPasswordVisible by remember{ mutableStateOf(false) }
                OutlinedTextField(value = passwordValue,
                    onValueChange = { passwordValue = it },
                    label = { Text(text = stringResource(id = R.string.card_password))},
                    singleLine = true,
                    enabled = isCheckboxChecked,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None
                            else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        ),
                    trailingIcon = {
                        Icon(painter = if(isPasswordVisible)
                            painterResource(id = R.drawable.ic_visibility_off)
                            else painterResource(id = R.drawable.ic_visibility),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { isPasswordVisible = !isPasswordVisible })
                    })

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isCheckboxChecked,
                        onCheckedChange = {isCheckboxChecked = it})
                    Text(text = stringResource(id = R.string.card_enable_pass))

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = stringResource(id = R.string.card_hide_fab))
                    var isSwitchChecked by remember{ mutableStateOf(true) }
                    Switch(checked = isSwitchChecked,
                        onCheckedChange = {isSwitchChecked = it}, 
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.common_padding_min)))
                }

                val context = LocalContext.current
                var sliderValue by remember{ mutableStateOf(6f) }
                Slider(value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        urlValue = "Vol: ${it.toInt()}"
                    },
                    onValueChangeFinished = {
                        Toast.makeText(context, "Vol: $sliderValue", Toast.LENGTH_SHORT).show()
                    },
                    valueRange = 0f..10f,
                    steps = 4)

                val emailValue by remember{ mutableStateOf("ant@frogames.es") }
                var cpVisible by remember{ mutableStateOf(true) }
                if (cpVisible){
                    Chip(onClick = { Toast.makeText(context, emailValue, Toast.LENGTH_SHORT).show() },
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.common_padding_default))) {
                        Text(text = emailValue)
                        Icon(imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { cpVisible = false })
                    }
                }

                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.common_padding_middle)))

                val colors = listOf("Red", "Blue", "Green")
                SegmentedControl(items = colors,
                    defaultSelectedItemIndex = -1,
                    cornerRadius = 48,
                    color = R.color.purple_200,
                    onItemSelection = {
                        colorMain = when(it){
                            0 -> Color.Red
                            1 -> Color.Blue
                            else -> Color.Green
                        }
                    })
            }
        }
    }
}