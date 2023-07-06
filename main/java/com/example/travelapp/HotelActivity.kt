package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.TravelAppTheme

class HotelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    app(this)
                }
            }
        }
    }
}



data class Hotel(var city:String,var name:String,var image:Int,var des:String,
                 var rating: Double,var price:Double)

data class Image(var city: String,var image:Int)

@Composable
fun showHotels(hotels: List<Hotel>, cityImage: Int,context: Context) {
    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = cityImage),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "EXPLORE DESTINATIONS In CITIES",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 43.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
        items(hotels.size) { i ->
            Card(
                modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color(0xFFFF8C00),
                contentColor = Color.White
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Image(
                        painter = painterResource(id = hotels[i].image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(250.dp)
                            .clip(RoundedCornerShape(10))
                    )
                    Box(modifier = Modifier
                        .background(
                            color = Color(0xFF39C853),
                            shape = RoundedCornerShape(80)
                        )
                        .clip(shape = RoundedCornerShape(80))
                        .padding(start = 5.dp, end = 5.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Star,
                                contentDescription = "")
                            Text(text = hotels[i].rating.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp)
                            Column(modifier=Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Bottom) {
                                Text(text = "/5",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0x6D000000),
                                )
                            }
                        }
                    }
                    Text(
                        text = hotels[i].name,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight =37.sp
                    )
                    Text(text = hotels[i].des)
                    Spacer(modifier =Modifier.height(5.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = {
                            val intent = Intent(context, NetbankActivity::class.java)
                            context.startActivity(intent)},
                            modifier = Modifier) {
                            Text(text = "Book Room")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box (modifier = Modifier.width(IntrinsicSize.Min)){
                                Row(verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.rupee),
                                        contentDescription = "", modifier = Modifier.size(14.dp),
                                    )
                                    Text(text = hotels[i].price.toString())
                                }
                                Divider(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                    thickness = 2.dp, color = Color.Black)
                            }
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.rupee),
                                    contentDescription = "", modifier = Modifier
                                        .size(25.dp)
                                        .align(Alignment.CenterVertically)
                                )
                                Text(text = (hotels[i].price-((30/100)*hotels[i].price)).toString(),
                                    fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun addAndGiveAllHotels():List<Hotel>{
    var hotels= mutableListOf<Hotel>()
    hotels.add(Hotel("Bali",
        "Best Western Premier Agung Resort Ubud",
        R.drawable.agung,
        "Enjoy a restful vacation at the Best Western Premier Agung Resort Ubud in Bali, which has comfortable rooms, infinity pools, a full-service spa, gym, wellness centre, banqueting facility, 2 dining options and a business centre.",
        4.5,8551.0))
    hotels.add(Hotel("Bali","SenS Hotel & Spa Conference Ubud",
        R.drawable.img,
        "Experience the perfect getaway with a stay at the SenS Hotel & Spa Conference Ubud, offering a plush spa, an expansive outdoor pool and a well-equipped fitness centre.",
        4.5,110312.0
    ))
    hotels.add(Hotel("Bali","Grand Zuri Kuta Bali",
        R.drawable.img_1,
        "Experience the perfect getaway with a stay at the Grand Zuri Kuta Bali, offering an expansive pool, a fitness centre and plush spa.",
        4.5,1856.0
    ))
    hotels.add(Hotel("Bali","MERUSAKA Nusa Dua",
        R.drawable.img_2,
        "Located close to the Bali Golf Club, Merusaka Nusa Duafeatures several lagoon-style pools, 6 dining options, a spa, kids' club, business centre, banquet, meeting and conference facilities and more.",
        4.5,29625.0
    ))
    hotels.add(Hotel("Bali",
        "Ashoka Tree Resort at Tanggayuda",
        R.drawable.img_3,
        "Perched on the slopes of Ubud, the Ashoka Tree Resort at Tanggayuda features superb rooms, spa, gym, wellness centre, banquet facility, billiards table, 2 outdoor pools, 2 dining options, a kid’s pool and business centre.",
        4.5,7072.0
    ))
    hotels.add(Hotel("Bali",
        "Ramada Encore by Wyndham Bali Seminyak",
        R.drawable.img_4,
        "Experience the perfect getaway with a stay at this 5-star secluded heaven, W Bali-Seminyak, offering an expansive pool, fitness centre and a plush spa.",
        4.5,3117.0
    ))
    hotels.add(Hotel("Paris",
        "Best Western Premier Agung Resort Ubud",
        R.drawable.agung,
        "Enjoy a restful vacation at the Best Western Premier Agung Resort Ubud in Bali, which has comfortable rooms, infinity pools, a full-service spa, gym, wellness centre, banqueting facility, 2 dining options and a business centre.",
        4.5,8551.0))
    hotels.add(Hotel("Paris","SenS Hotel & Spa Conference Ubud",
        R.drawable.img,
        "Experience the perfect getaway with a stay at the SenS Hotel & Spa Conference Ubud, offering a plush spa, an expansive outdoor pool and a well-equipped fitness centre.",
        4.5,110312.0
    ))
    hotels.add(Hotel("Paris","Grand Zuri Kuta Bali",
        R.drawable.img_1,
        "Experience the perfect getaway with a stay at the Grand Zuri Kuta Bali, offering an expansive pool, a fitness centre and plush spa.",
        4.5,1856.0
    ))
    hotels.add(Hotel("Paris","MERUSAKA Nusa Dua",
        R.drawable.img_2,
        "Located close to the Bali Golf Club, Merusaka Nusa Duafeatures several lagoon-style pools, 6 dining options, a spa, kids' club, business centre, banquet, meeting and conference facilities and more.",
        4.5,29625.0
    ))
    hotels.add(Hotel("Paris",
        "Ashoka Tree Resort at Tanggayuda",
        R.drawable.img_3,
        "Perched on the slopes of Ubud, the Ashoka Tree Resort at Tanggayuda features superb rooms, spa, gym, wellness centre, banquet facility, billiards table, 2 outdoor pools, 2 dining options, a kid’s pool and business centre.",
        4.5,7072.0
    ))
    hotels.add(Hotel("Singapore",
        "Ramada Encore by Wyndham Bali Seminyak",
        R.drawable.img_4,
        "Experience the perfect getaway with a stay at this 5-star secluded heaven, W Bali-Seminyak, offering an expansive pool, fitness centre and a plush spa.",
        4.5,3117.0
    ))
    hotels.add(Hotel("Singapore",
        "Best Western Premier Agung Resort Ubud",
        R.drawable.agung,
        "Enjoy a restful vacation at the Best Western Premier Agung Resort Ubud in Bali, which has comfortable rooms, infinity pools, a full-service spa, gym, wellness centre, banqueting facility, 2 dining options and a business centre.",
        4.5,8551.0))
    hotels.add(Hotel("Singapore","SenS Hotel & Spa Conference Ubud",
        R.drawable.img,
        "Experience the perfect getaway with a stay at the SenS Hotel & Spa Conference Ubud, offering a plush spa, an expansive outdoor pool and a well-equipped fitness centre.",
        4.5,110312.0
    ))
    hotels.add(Hotel("Singapore","Grand Zuri Kuta Bali",
        R.drawable.img_1,
        "Experience the perfect getaway with a stay at the Grand Zuri Kuta Bali, offering an expansive pool, a fitness centre and plush spa.",
        4.5,1856.0
    ))
    hotels.add(Hotel("Singapore","MERUSAKA Nusa Dua",
        R.drawable.img_2,
        "Located close to the Bali Golf Club, Merusaka Nusa Duafeatures several lagoon-style pools, 6 dining options, a spa, kids' club, business centre, banquet, meeting and conference facilities and more.",
        4.5,29625.0
    ))
    hotels.add(Hotel("Singapore",
        "Ashoka Tree Resort at Tanggayuda",
        R.drawable.img_3,
        "Perched on the slopes of Ubud, the Ashoka Tree Resort at Tanggayuda features superb rooms, spa, gym, wellness centre, banquet facility, billiards table, 2 outdoor pools, 2 dining options, a kid’s pool and business centre.",
        4.5,7072.0
    ))
    hotels.add(Hotel("Singapore",
        "Ramada Encore by Wyndham Bali Seminyak",
        R.drawable.img_4,
        "Experience the perfect getaway with a stay at this 5-star secluded heaven, W Bali-Seminyak, offering an expansive pool, fitness centre and a plush spa.",
        4.5,3117.0
    ))
    return hotels
}

@Composable
fun app(context: Context){
    showHotels(addAndGiveHotelsInCity(hotels = addAndGiveAllHotels(),
        city = "Singapore"),
        getImage("Singapore"),
        context)
}


@Composable
fun addAndGiveHotelsInCity(hotels: List<Hotel>, city: String):List<Hotel>{
    var hotelsInCity= mutableListOf<Hotel>()

    for (i in hotels){
        if(i.city==city){
            hotelsInCity.add(i)
        }
    }
    return hotelsInCity
}

fun getImage(city: String): Int {
    return when (city) {
        "Bali" -> R.drawable.bali_1
        "Paris" -> R.drawable.paris_1
        "Singapore" -> R.drawable.singapore_1
        else -> R.drawable.img
    }
}