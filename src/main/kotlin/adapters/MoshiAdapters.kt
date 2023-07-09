package adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import model.setup.Setup
import model.output.Wrapper

val moshiAdapterWrapper: JsonAdapter<Wrapper> = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
    .adapter(Wrapper::class.java)

val moshiAdapterSetup: JsonAdapter<Setup> = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
    .adapter(Setup::class.java)