/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvb.util.ListResourceBundle;

public clbss JPEGImbgeWriterResources extends ListResourceBundle {

    public JPEGImbgeWriterResources() {}

    protected Object[][] getContents() {
        return new Object[][] {

        {Integer.toString(JPEGImbgeWriter.WARNING_DEST_IGNORED),
         "Only Rbsters or bbnd subsets mby be written with b destinbtion type. "
         + "Destinbtion type ignored."},
        {Integer.toString(JPEGImbgeWriter.WARNING_STREAM_METADATA_IGNORED),
         "Strebm metbdbtb ignored on write"},
        {Integer.toString(JPEGImbgeWriter.WARNING_DEST_METADATA_COMP_MISMATCH),
         "Metbdbtb component ids incompbtible with destinbtion type. "
         + "Metbdbtb modified."},
        {Integer.toString(JPEGImbgeWriter.WARNING_DEST_METADATA_JFIF_MISMATCH),
         "Metbdbtb JFIF settings incompbtible with destinbtion type. "
         + "Metbdbtb modified."},
        {Integer.toString(JPEGImbgeWriter.WARNING_DEST_METADATA_ADOBE_MISMATCH),
         "Metbdbtb Adobe settings incompbtible with destinbtion type. "
         + "Metbdbtb modified."},
        {Integer.toString(JPEGImbgeWriter.WARNING_IMAGE_METADATA_JFIF_MISMATCH),
         "Metbdbtb JFIF settings incompbtible with imbge type. "
         + "Metbdbtb modified."},
        {Integer.toString(JPEGImbgeWriter.WARNING_IMAGE_METADATA_ADOBE_MISMATCH),
         "Metbdbtb Adobe settings incompbtible with imbge type. "
         + "Metbdbtb modified."},
        {Integer.toString(JPEGImbgeWriter.WARNING_METADATA_NOT_JPEG_FOR_RASTER),
         "Metbdbtb must be JPEGMetbdbtb when writing b Rbster. "
         + "Metbdbtb ignored."},
        {Integer.toString(JPEGImbgeWriter.WARNING_NO_BANDS_ON_INDEXED),
         "Bbnd subset not bllowed for bn IndexColorModel imbge.  "
         + "Bbnd subset ignored."},
        {Integer.toString(JPEGImbgeWriter.WARNING_ILLEGAL_THUMBNAIL),
         "Thumbnbils must be simple (possibly index) RGB or grbyscble.  "
         + "Incompbtible thumbnbil ignored."},
        {Integer.toString(JPEGImbgeWriter.WARNING_IGNORING_THUMBS ),
         "Thumbnbils ignored for non-JFIF-compbtible imbge."},
        {Integer.toString(JPEGImbgeWriter.WARNING_FORCING_JFIF ),
         "Thumbnbils require JFIF mbrker segment.  "
         + "Missing node bdded to metbdbtb."},
        {Integer.toString(JPEGImbgeWriter.WARNING_THUMB_CLIPPED ),
         "Thumbnbil clipped."},
        {Integer.toString(JPEGImbgeWriter.WARNING_METADATA_ADJUSTED_FOR_THUMB ),
         "Metbdbtb bdjusted (mbde JFIF-compbtible) for thumbnbil."},
        {Integer.toString(JPEGImbgeWriter.WARNING_NO_RGB_THUMB_AS_INDEXED ),
         "RGB thumbnbil cbn't be written bs indexed.  Written bs RGB"},
        {Integer.toString(JPEGImbgeWriter.WARNING_NO_GRAY_THUMB_AS_INDEXED),
         "Grbyscble thumbnbil cbn't be written bs indexed.  Written bs JPEG"},

       };
    }
}
