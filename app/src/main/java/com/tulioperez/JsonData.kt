package com.tulioperez

import com.google.gson.annotations.SerializedName

data class JsonData(
    @SerializedName("collection")
    val collection: Collection
) {
    data class Collection(
        @SerializedName("href")
        val href: String,
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("links")
        val links: List<Link>,
        @SerializedName("metadata")
        val metadata: Metadata,
        @SerializedName("version")
        val version: String
    ) {
        data class Item(
            @SerializedName("data")
            val `data`: List<Data>,
            @SerializedName("href")
            val href: String,
            @SerializedName("links")
            val links: List<Link>
        ) {
            data class Data(
                @SerializedName("center")
                val center: String,
                @SerializedName("date_created")
                val dateCreated: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("description_508")
                val description508: String,
                @SerializedName("keywords")
                val keywords: List<String>,
                @SerializedName("media_type")
                val mediaType: String,
                @SerializedName("nasa_id")
                val nasaId: String,
                @SerializedName("secondary_creator")
                val secondaryCreator: String,
                @SerializedName("title")
                val title: String
            )

            data class Link(
                @SerializedName("href")
                val href: String,
                @SerializedName("rel")
                val rel: String,
                @SerializedName("render")
                val render: String
            )
        }

        data class Link(
            @SerializedName("href")
            val href: String,
            @SerializedName("prompt")
            val prompt: String,
            @SerializedName("rel")
            val rel: String
        )

        data class Metadata(
            @SerializedName("total_hits")
            val totalHits: Int
        )
    }
}