package de.cinelog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.cinelog.utils.dummy.dummyMovies

@Preview
@Composable
fun CustomSearchTextField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    screen: Screen,
    singleLine: Boolean = true,
) {
    var isFocused by remember { mutableStateOf(false) }
    var selectedFromDropdown by remember { mutableStateOf(false) }
    var textState by remember {
        mutableStateOf(
            TextFieldValue(
                value, selection = TextRange(value.length)
            )
        )
    }
    val filtered = remember(textState.text) {
        val input = textState.text.trim().replace(" ", "")
        if (input.isEmpty()) emptyList()
        else dummyMovies().map { movie -> movie.title }
            .filter { it.replace(" ", "").contains(input, ignoreCase = true) }.take(3)
    }
    var dropdownVisible by remember { mutableStateOf(false) }
    LaunchedEffect(textState.text, isFocused, selectedFromDropdown) {
        dropdownVisible = isFocused && filtered.isNotEmpty() && !selectedFromDropdown
    }
    Surface(
        shape = RoundedCornerShape(10),
        shadowElevation = 2.dp,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                BasicTextField(
                    value = textState,
                    onValueChange = { newValue ->
                        textState = newValue
                        selectedFromDropdown = false
                        onValueChange(newValue.text)
                    },
                    singleLine = singleLine,
                    textStyle = TextStyle(fontSize = 16.sp),
                    cursorBrush = screen.gradient,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                        .onFocusChanged { isFocused = it.isFocused }) { innerTextField ->
                    if (textState.text.isEmpty()) {
                        Text(text = labelText, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            }
            if (dropdownVisible) {
                Box(
                    modifier = Modifier.fillMaxWidth().offset(y = (-4).dp)
                ) {
                    Column {
                        filtered.forEachIndexed { index, suggestion ->
                            Row(modifier = Modifier.fillMaxWidth().clickable {
                                textState = TextFieldValue(
                                    suggestion, selection = TextRange(suggestion.length)
                                )
                                selectedFromDropdown = true
                                dropdownVisible = false
                                onValueChange(suggestion)
                            }.padding(horizontal = 16.dp, vertical = 12.dp)) {
                                Text(
                                    text = suggestion, fontSize = 16.sp
                                )
                            }
                            if (index < filtered.size - 1) {
                                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
                            }
                        }
                    }
                }
            }
        }
    }
}

val imdbTop100 = listOf(
    "The Shawshank Redemption",
    "The Godfather",
    "The Dark Knight",
    "The Godfather Part II",
    "12 Angry Men",
    "Schindler's List",
    "The Lord of the Rings: The Return of the King",
    "Pulp Fiction",
    "The Lord of the Rings: The Fellowship of the Ring",
    "The Good, the Bad and the Ugly",
    "Fight Club",
    "Forrest Gump",
    "Inception",
    "The Lord of the Rings: The Two Towers",
    "Star Wars: Episode V - The Empire Strikes Back",
    "The Matrix",
    "Goodfellas",
    "One Flew Over the Cuckoo's Nest",
    "Seven Samurai",
    "Se7en",
    "City of God",
    "The Silence of the Lambs",
    "It's a Wonderful Life",
    "Life Is Beautiful",
    "Spirited Away",
    "Saving Private Ryan",
    "Interstellar",
    "The Green Mile",
    "Parasite",
    "Leon: The Professional",
    "The Usual Suspects",
    "Harakiri",
    "The Lion King",
    "Back to the Future",
    "Terminator 2: Judgment Day",
    "American History X",
    "Modern Times",
    "Gladiator",
    "Raiders of the Lost Ark",
    "Whiplash",
    "The Prestige",
    "Memento",
    "Apocalypse Now",
    "Alien",
    "The Great Dictator",
    "Sunset Boulevard",
    "Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb",
    "Joker",
    "Django Unchained",
    "The Shining",
    "Paths of Glory",
    "WALL·E",
    "Princess Mononoke",
    "Oldboy",
    "Once Upon a Time in the West",
    "Grave of the Fireflies",
    "Rear Window",
    "Coco",
    "Your Name.",
    "Avengers: Infinity War",
    "Toy Story",
    "Toy Story 3",
    "Braveheart",
    "Amélie",
    "Aliens",
    "Citizen Kane",
    "A Separation",
    "Die Hard",
    "Batman Begins",
    "Amadeus",
    "Taxi Driver",
    "Full Metal Jacket",
    "Double Indemnity",
    "2001: A Space Odyssey",
    "The Dark Knight Rises",
    "The Wolf of Wall Street",
    "Once Upon a Time in America",
    "Princess Mononoke",
    "Casablanca",
    "Indiana Jones and the Last Crusade",
    "Raiders of the Lost Ark",
    "The Pianist",
    "Blade Runner 2049",
    "The Terminator",
    "Toy Story 2",
    "Eternal Sunshine of the Spotless Mind",
    "La La Land",
    "The Irishman",
    "Logan",
    "Mad Max: Fury Road",
    "1917",
    "The Sixth Sense",
    "Blade Runner"
)