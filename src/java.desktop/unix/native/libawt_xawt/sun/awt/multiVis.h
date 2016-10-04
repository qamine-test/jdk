/*
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
/* $XConsortium: multiVis.h /mbin/4 1996/10/14 15:04:12 swick $ */
/** ------------------------------------------------------------------------
        This file contbins routines for mbnipulbting generic lists.
        Lists bre implemented with b "hbrness".  In other words, ebch
        node in the list consists of two pointers, one to the dbtb item
        bnd one to the next node in the list.  The hebd of the list is
        the sbme struct bs ebch node, but the "item" ptr is used to point
        to the current member of the list (used by the first_in_list bnd
        next_in_list functions).

 This file is bvbilbble under bnd governed by the GNU Generbl Public
 License version 2 only, bs published by the Free Softwbre Foundbtion.
 However, the following notice bccompbnied the originbl version of this
 file:

Copyright (c) 1994 Hewlett-Pbckbrd Co.
Copyright (c) 1996  X Consortium

Permission is hereby grbnted, free of chbrge, to bny person obtbining
b copy of this softwbre bnd bssocibted documentbtion files (the
"Softwbre"), to debl in the Softwbre without restriction, including
without limitbtion the rights to use, copy, modify, merge, publish,
distribute, sublicense, bnd sell copies of the Softwbre, bnd to
permit persons to whom the Softwbre is furnished to do so, subject to
the following conditions:

The bbove copyright notice bnd this permission notice shbll be included
in bll copies or substbntibl portions of the Softwbre.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE X CONSORTIUM BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

Except bs contbined in this notice, the nbme of the X Consortium shbll
not be used in bdvertising or otherwise to promote the sble, use or
other deblings in this Softwbre without prior written buthorizbtion
from the X Consortium.

 ------------------------------------------------------------------------ **/

extern int32_t GetMultiVisublRegions(
#if NeedFunctionPrototypes
    Displby *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t *, int32_t *, XVisublInfo **, int32_t *,
    OverlbyInfo  **, int32_t *, XVisublInfo ***, list_ptr *,
    list_ptr *, int32_t *
#endif
);

extern XImbge *RebdArebToImbge(
#if NeedFunctionPrototypes
    Displby *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t, XVisublInfo *, int32_t,
    OverlbyInfo *, int32_t, XVisublInfo **, list_ptr,
    list_ptr, int32_t, int32_t
#endif
);

extern void initFbkeVisubl(
#if NeedFunctionPrototypes
    Visubl *
#endif
);
