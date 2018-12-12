/*
 *    Transportr
 *
 *    Copyright (c) 2013 - 2018 Torsten Grote
 *
 *    This program is Free Software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as
 *    published by the Free Software Foundation, either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.grobox.transportr.trips

import de.grobox.transportr.locations.WrapLocation
import de.schildbach.pte.dto.Product
import java.util.*

class TripQuery internal constructor(
        val from: WrapLocation, val via: WrapLocation?, val to: WrapLocation,
        val date: Date,
        departure: Boolean?,
        products: Set<Product>?) {

    val departure = departure != false
    val products: Set<Product> = products ?: EnumSet.allOf(Product::class.java)

}
