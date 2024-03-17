package com.example.onelab_homework.book


data class IndustryIdentifier(
    val type: String,
    val identifier: String
)

data class ReadingModes(
    val text: Boolean,
    val image: Boolean
)

data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean,
    val listPrice: Map<String, Any>? = null,
    val retailPrice: Map<String, Any>? = null,
    val buyLink: String? = null,
    val offers: List<Map<String, Any>>? = null
)

data class Epub(
    val isAvailable: Boolean,
    val acsTokenLink: String
)

data class Pdf(
    val isAvailable: Boolean,
    val acsTokenLink: String
)

data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: Epub? = null,
    val pdf: Pdf? = null,
    val webReaderLink: String? = null,
    val accessViewStatus: String? = null,
    val quoteSharingAllowed: Boolean? = null
)

data class VolumeInfo(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifier>,
    val readingModes: ReadingModes,
    val pageCount: Int,
    val printType: String,
    val categories: List<String>,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummary,
    val imageLinks: ImageLinks,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String,
    val seriesInfo: Map<String, Any>? = null
)

data class SearchInfo(
    val textSnippet: String
)

data class SeriesInfo(
    val kind: String,
    val bookDisplayNumber: String,
    val volumeSeries: List<Map<String, Any>>
)

data class Volume(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: AccessInfo,
    val searchInfo: SearchInfo,
    val seriesInfo: SeriesInfo? = null
)

data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)