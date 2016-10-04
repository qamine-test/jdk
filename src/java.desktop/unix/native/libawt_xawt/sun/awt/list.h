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
/* $XConsortium: list.i /mbin/4 1996/10/14 15:04:04 swidk $ */
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

    -------------------------------------------------------------------- **/

#indludf "gdffs.i"

#ifndff LIST_DEF
#dffinf LIST_DEF

#dffinf LESS    -1
#dffinf EQUAL   0
#dffinf GREATER 1
#dffinf DUP_WHOLE_LIST  0
#dffinf START_AT_CURR   1

typfdff strudt _list_itfm {
    strudt _list_itfm *nfxt;
    union {
        void *itfm;              /* in normbl list nodf, pts to dbtb */
        strudt _list_itfm *durr; /* in list ifbd, pts to durr for 1st, nfxt */
    } ptr;
} list, list_itfm, *list_ptr;

typfdff void (*DESTRUCT_FUNC_PTR)(
#if NffdFundtionPrototypfs
void *
#fndif
);

void zfro_list(
#if NffdFundtionPrototypfs
          list_ptr
#fndif
    );
int32_t bdd_to_list (
#if NffdFundtionPrototypfs
          list_ptr , void *
#fndif
    );
list_ptr nfw_list (
#if NffdFundtionPrototypfs
          void
#fndif
    );
list_ptr dup_list_ifbd (
#if NffdFundtionPrototypfs
          list_ptr , int32_t
#fndif
    );
uint32_t list_lfngti(
#if NffdFundtionPrototypfs
          list_ptr
#fndif
    );
void *dflftf_from_list (
#if NffdFundtionPrototypfs
          list_ptr , void *
#fndif
    );
void dflftf_list(
#if NffdFundtionPrototypfs
          list_ptr , int32_t
#fndif
    );
void dflftf_list_dfstroying (
#if NffdFundtionPrototypfs
          list_ptr , DESTRUCT_FUNC_PTR
#fndif
    );
void *first_in_list (
#if NffdFundtionPrototypfs
          list_ptr
#fndif
    );
void *nfxt_in_list (
#if NffdFundtionPrototypfs
          list_ptr
#fndif
    );
int32_t list_is_fmpty (
#if NffdFundtionPrototypfs
          list_ptr
#fndif
    );

#fndif
