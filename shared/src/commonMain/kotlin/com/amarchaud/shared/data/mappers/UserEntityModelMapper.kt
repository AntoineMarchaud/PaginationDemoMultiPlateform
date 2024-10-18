package com.amarchaud.shared.data.mappers

import com.amarchaud.database.UsersEntity
import com.amarchaud.shared.data.db.SqlDelightConverter
import com.amarchaud.shared.domain.models.CoordinatesModel
import com.amarchaud.shared.domain.models.DobModel
import com.amarchaud.shared.domain.models.IdModel
import com.amarchaud.shared.domain.models.LocationModel
import com.amarchaud.shared.domain.models.NameModel
import com.amarchaud.shared.domain.models.PictureModel
import com.amarchaud.shared.domain.models.RegisteredModel
import com.amarchaud.shared.domain.models.StreetModel
import com.amarchaud.shared.domain.models.TimezoneModel
import com.amarchaud.shared.domain.models.UserModel

internal fun UsersEntity.toDomain() = UserModel(
    localId = this._id,
    gender = this.gender,
    name = NameModel(
        title = this.name_title,
        first = this.name_first,
        last = this.name_last
    ),
    location = LocationModel(
        street = StreetModel(
            number = this.location_street_number,
            name = this.location_street_name
        ),
        city = this.location_city,
        state = this.location_state,
        country = this.location_country,
        postcode = this.location_postcode,
        coordinates = CoordinatesModel(
            latitude = this.location_coordinates_latitude,
            longitude = this.location_coordinates_longitude
        ),
        timezone = TimezoneModel(
            offset = this.location_timezone_offset,
            description = this.location_timezone_description
        )
    ),
    email = this.email,
    dob = DobModel(
        date = this.dob_date?.let {
            SqlDelightConverter.LocalDateRoomConverter.stringToLocalDate(it)
        },
        age = this.dob_age
    ),
    registered = RegisteredModel(
        date = this.registered_date?.let {
            SqlDelightConverter.LocalDateRoomConverter.stringToLocalDate(it)
        },
        age = this.registered_age
    ),
    phone = null,
    cell = null,
    id = IdModel(),
    picture = PictureModel(
        large = this.picture_large,
        medium = this.picture_medium,
        thumbnail = this.picture_thumbnail
    ),
    nat = this.nat
)
