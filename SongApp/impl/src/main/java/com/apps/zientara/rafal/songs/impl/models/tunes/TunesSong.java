package com.apps.zientara.rafal.songs.impl.models.tunes;

import com.apps.rafal.zientara.songs.core.helpers.SongModelMatcher;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Calendar;
import java.util.Date;

@Parcel(Parcel.Serialization.BEAN)
public class TunesSong implements SongModel {

    @SerializedName("wrapperType")
    String wrapperType;

    @SerializedName("kind")
    String kind;

    @SerializedName("artistId")
    Long artistId;

    @SerializedName("collectionId")
    Long collectionId;

    @SerializedName("trackId")
    Long trackId;

    @SerializedName("artistName")
    String artistName;

    @SerializedName("collectionName")
    String collectionName;

    @SerializedName("trackName")
    String trackName;

    @SerializedName("collectionCensoredName")
    String collectionCensoredName;

    @SerializedName("trackCensoredName")
    String trackCensoredName;

    @SerializedName("artistViewUrl")
    String artistViewUrl;

    @SerializedName("collectionViewUrl")
    String collectionViewUrl;

    @SerializedName("trackViewUrl")
    String trackViewUrl;

    @SerializedName("previewUrl")
    String previewUrl;

    @SerializedName("artworkUrl30")
    String artworkUrl30;

    @SerializedName("artworkUrl60")
    String artworkUrl60;

    @SerializedName("artworkUrl100")
    String artworkUrl100;

    @SerializedName("collectionPrice")
    Float collectionPrice;

    @SerializedName("trackPrice")
    Float trackPrice;

    @SerializedName("releaseDate")
    Date releaseDate;

    @SerializedName("collectionExplicitness")
    String collectionExplicitness;

    @SerializedName("discCount")
    Integer discCount;

    @SerializedName("discNumber")
    Integer discNumber;

    @SerializedName("trackCount")
    Integer trackCount;

    @SerializedName("trackNumber")
    Integer trackNumber;

    @SerializedName("trackTimeMillis")
    Long trackTimeMillis;

    @SerializedName("country")
    String country;

    @SerializedName("currency")
    String currency;

    @SerializedName("primaryGenreName")
    String primaryGenreName;

    @SerializedName("isStreamable")
    String isStreamable;

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public Float getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Float trackPrice) {
        this.trackPrice = trackPrice;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Long trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(String isStreamable) {
        this.isStreamable = isStreamable;
    }

    @Override
    public String getArtist() {
        return artistName;
    }

    @Override
    public Integer getReleaseYear() {
        if (releaseDate != null)
            return getYear();
        return 0;
    }

    private int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(releaseDate);
        return cal.get(Calendar.YEAR);
    }

    @Override
    public String getSongName() {
        return trackName;
    }

    @Override
    public boolean matchesQuery(String[] words) {
        for (String word : words) {
            if (matchesAny(word))
                return true;
        }
        return false;
    }

    private boolean matchesAny(String word) {
        return SongModelMatcher.matches(this, word);//// TODO: 29.08.2017 check more
    }
}
