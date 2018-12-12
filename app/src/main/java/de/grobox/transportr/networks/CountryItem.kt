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

package de.grobox.transportr.networks

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewCompat
import android.view.View
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import com.mikepenz.fastadapter.listeners.OnClickListener
import de.grobox.transportr.R

internal class CountryItem(private val country: Country) : AbstractExpandableItem<CountryItem, CountryViewHolder, TransportNetworkItem>() {

    init {
        withSubItems(country.getSubItems())
    }

    fun getName(context: Context): String {
        return country.getName(context)
    }

    @IdRes
    override fun getType(): Int {
        return R.id.list_item_transport_country
    }

    @LayoutRes
    override fun getLayoutRes(): Int {
        return R.layout.list_item_transport_country
    }

    override fun bindView(ui: CountryViewHolder, payloads: List<Any>) {
        super.bindView(ui, payloads)
        ui.bind(country, isExpanded)
    }

    override fun getViewHolder(view: View): CountryViewHolder {
        return CountryViewHolder(view)
    }

    override fun getIdentifier(): Long {
        return country.name.toLong()
    }

    override fun getOnItemClickListener(): OnClickListener<CountryItem> {
        return OnClickListener { v, _, item, _ ->
            if (v == null) return@OnClickListener false
            if (!item.isExpanded) {
                ViewCompat.animate(v.findViewById(R.id.chevron)).rotation(180f).start()
            } else {
                ViewCompat.animate(v.findViewById(R.id.chevron)).rotation(0f).start()
            }
            true
        }
    }

}
