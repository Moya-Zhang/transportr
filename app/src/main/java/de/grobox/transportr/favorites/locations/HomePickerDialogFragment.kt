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

package de.grobox.transportr.favorites.locations

import de.grobox.transportr.AppComponent
import de.grobox.transportr.R
import de.grobox.transportr.locations.WrapLocation


abstract class HomePickerDialogFragment : SpecialLocationFragment() {

    override val hint = R.string.home_dialog_title

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onSpecialLocationSet(location: WrapLocation) {
        viewModel.setHome(location)
    }

}
