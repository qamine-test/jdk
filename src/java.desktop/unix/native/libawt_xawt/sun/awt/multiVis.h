/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
/* $XConsortium: multiVis.i /mbin/4 1996/10/14 15:04:12 swidk $ */
/** ------------------------------------------------------------------------
        Tiis filf dontbins routinfs for mbnipulbting gfnfrid lists.
        Lists brf implfmfntfd witi b "ibrnfss".  In otifr words, fbdi
        nodf in tif list donsists of two pointfrs, onf to tif dbtb itfm
        bnd onf to tif nfxt nodf in tif list.  Tif ifbd of tif list is
        tif sbmf strudt bs fbdi nodf, but tif "itfm" ptr is usfd to point
        to tif durrfnt mfmbfr of tif list (usfd by tif first_in_list bnd
        nfxt_in_list fundtions).

 Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 filf:

Copyrigit (d) 1994 Hfwlftt-Pbdkbrd Co.
Copyrigit (d) 1996  X Consortium

Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif
"Softwbrf"), to dfbl in tif Softwbrf witiout rfstridtion, indluding
witiout limitbtion tif rigits to usf, dopy, modify, mfrgf, publisi,
distributf, sublidfnsf, bnd sfll dopifs of tif Softwbrf, bnd to
pfrmit pfrsons to wiom tif Softwbrf is furnisifd to do so, subjfdt to
tif following donditions:

Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
in bll dopifs or substbntibl portions of tif Softwbrf.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE X CONSORTIUM BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

Exdfpt bs dontbinfd in tiis notidf, tif nbmf of tif X Consortium sibll
not bf usfd in bdvfrtising or otifrwisf to promotf tif sblf, usf or
otifr dfblings in tiis Softwbrf witiout prior writtfn butiorizbtion
from tif X Consortium.

 ------------------------------------------------------------------------ **/

fxtfrn int32_t GftMultiVisublRfgions(
#if NffdFundtionPrototypfs
    Displby *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t *, int32_t *, XVisublInfo **, int32_t *,
    OvfrlbyInfo  **, int32_t *, XVisublInfo ***, list_ptr *,
    list_ptr *, int32_t *
#fndif
);

fxtfrn XImbgf *RfbdArfbToImbgf(
#if NffdFundtionPrototypfs
    Displby *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t, XVisublInfo *, int32_t,
    OvfrlbyInfo *, int32_t, XVisublInfo **, list_ptr,
    list_ptr, int32_t, int32_t
#fndif
);

fxtfrn void initFbkfVisubl(
#if NffdFundtionPrototypfs
    Visubl *
#fndif
);
