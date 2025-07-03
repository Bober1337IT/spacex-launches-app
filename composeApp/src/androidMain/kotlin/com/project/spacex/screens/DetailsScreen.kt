package com.project.spacex.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.AppTheme
import com.example.compose.app_theme_successful
import com.example.compose.app_theme_unsuccessful
import org.koin.androidx.compose.koinViewModel
import android.content.Intent
import android.net.Uri
import com.project.spacex.RocketLaunchViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    flightId: Int?,
    onClick: () -> Unit
) {
    val viewModel = koinViewModel<RocketLaunchViewModel>()
    val state by viewModel.state
    val context = LocalContext.current

    AppTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (flightId != null) {
                LaunchedEffect(flightId) {
                    viewModel.getLaunchById(flightId)
                }
            }

            when {
                state.isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Loading...",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                state.selectedLaunch != null -> {
                    val launch = state.selectedLaunch
                    Column {
                        Text(
                            text = "Mission: ${launch?.missionName}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Launch Date: ${launch?.launchDateUTC}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Status: ${if (launch?.launchSuccess == true) "Successful" else "Unsuccessful"}",
                            color = if (launch?.launchSuccess == true) app_theme_successful else app_theme_unsuccessful,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        launch?.details?.let { details ->
                            if (details.isNotBlank()) {
                                Text(
                                    text = "Details: $details",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                    launch?.links?.patch?.large?.let { patchLarge ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = patchLarge,
                                contentDescription = "Large patch image for ${launch.missionName}",
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(top = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Article Link:",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    launch?.links?.article?.let { article ->
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(article)
                                addStringAnnotation(
                                    tag = "URL",
                                    annotation = article,
                                    start = 0,
                                    end = length
                                )
                            }
                        }
                        ClickableText(
                            text = annotatedString,
                            style = MaterialTheme.typography.bodyLarge,
                            onClick = { offset ->
                                annotatedString.getStringAnnotations(
                                    tag = "URL",
                                    start = offset,
                                    end = offset
                                )
                                    .firstOrNull()?.let { annotation ->
                                        context.startActivity(
                                            Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                        )
                                    }
                            }
                        )
                    }

                    Button(onClick = onClick) {
                        Text("Back")
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Launch with ID $flightId not found",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}