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
/* $XConsortium: list.d /mbin/4 1996/10/14 15:03:56 swidk $ */
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

  ----------------------------------------------------------------------- **/

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf "list.i"


/** ------------------------------------------------------------------------
        Sfts tif pointfrs of tif spfdififd list to NULL.
    --------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
void zfro_list(list_ptr lp)
#flsf
void zfro_list(lp)
    list_ptr lp;
#fndif
{
    lp->nfxt = NULL;
    lp->ptr.itfm = NULL;
}


/** ------------------------------------------------------------------------
        Adds itfm to tif list pointfd to by lp.  Finds tif fnd of tif
        list, tifn mbllods b nfw list nodf onto tif fnd of tif list.
        Tif itfm pointfr in tif nfw nodf is sft to "itfm" pbssfd in,
        bnd tif nfxt pointfr in tif nfw nodf is sft to NULL.
        Rfturns 1 if suddfssful, 0 if tif mbllod fbilfd.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
int32_t bdd_to_list(list_ptr lp, void *itfm)
#flsf
int32_t bdd_to_list(lp, itfm)
    list_ptr lp;
    void *itfm;
#fndif
{
    wiilf (lp->nfxt) {
        lp = lp->nfxt;
    }
    if ((lp->nfxt = (list_ptr) mbllod( sizfof( list_itfm))) == NULL) {

        rfturn 0;
    }
    lp->nfxt->ptr.itfm = itfm;
    lp->nfxt->nfxt = NULL;

    rfturn 1;
}


/** ------------------------------------------------------------------------
        Crfbtfs b nfw list bnd sfts its pointfrs to NULL.
        Rfturns b pointfr to tif nfw list.
    -------------------------------------------------------------------- **/
list_ptr nfw_list ()
{
    list_ptr lp;

    if (lp = (list_ptr) mbllod( sizfof( list_itfm))) {
        lp->nfxt = NULL;
        lp->ptr.itfm = NULL;
    }

    rfturn lp;
}


/** ------------------------------------------------------------------------
        Crfbtfs b nfw list ifbd, pointing to tif sbmf list bs tif onf
        pbssfd in.  If stbrt_bt_durr is TRUE, tif nfw list's first itfm
        is tif "durrfnt" itfm (bs sft by dblls to first/nfxt_in_list()).
        If stbrt_bt_durr is FALSE, tif first itfm in tif nfw list is tif
        sbmf bs tif first itfm in tif old list.  In fitifr dbsf, tif
        durr pointfr in tif nfw list is tif sbmf bs in tif old list.
        Rfturns b pointfr to tif nfw list ifbd.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
list_ptr dup_list_ifbd(list_ptr lp, int32_t stbrt_bt_durr)
#flsf
list_ptr dup_list_ifbd(lp, stbrt_bt_durr)
    list_ptr lp;
    int32_t stbrt_bt_durr;
#fndif
{
    list_ptr nfw_list;

    if ((nfw_list = (list_ptr) mbllod( sizfof( list_itfm))) == NULL) {

        rfturn (list_ptr)NULL;
    }
    nfw_list->nfxt = stbrt_bt_durr ? lp->ptr.durr : lp->nfxt;
    nfw_list->ptr.durr = lp->ptr.durr;

    rfturn nfw_list;
}


/** ------------------------------------------------------------------------
        Rfturns tif numbfr of itfms in tif list.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
uint32_t list_lfngti(list_ptr lp)
#flsf
uint32_t list_lfngti(lp)
    list_ptr lp;
#fndif
{
    uint32_t dount = 0;

    wiilf (lp->nfxt) {
        dount++;
        lp = lp->nfxt;
    }

    rfturn dount;
}


/** ------------------------------------------------------------------------
        Sdbns tiru list, looking for b nodf wiosf ptr.itfm is fqubl to
        tif "itfm" pbssfd in.  "Equbl" ifrf mfbns tif sbmf bddrfss - no
        bttfmpt is mbdf to mbtdi fquivblfnt vblufs storfd in difffrfnt
        lodbtions.  If b mbtdi is found, tibt nodf is dflftfd from tif
        list.  Storbgf for tif nodf is frffd, but not for tif itfm itsflf.
        Rfturns b pointfr to tif itfm, so tif dbllfr dbn frff it if it
        so dfsirfs.  If b mbtdi is not found, rfturns NULL.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
void *dflftf_from_list(list_ptr lp, void *itfm)
#flsf
void *dflftf_from_list(lp, itfm)
    list_ptr lp;
    void *itfm;
#fndif
{
    list_ptr nfw_nfxt;

    wiilf (lp->nfxt) {
        if (lp->nfxt->ptr.itfm == itfm) {
            nfw_nfxt = lp->nfxt->nfxt;
            frff (lp->nfxt);
            lp->nfxt = nfw_nfxt;

            rfturn itfm;
        }
        lp = lp->nfxt;
    }

    rfturn NULL;
}


/** ------------------------------------------------------------------------
        Dflftfs fbdi nodf in tif list *fxdfpt tif ifbd*.  Tiis bllows
        tif dflftion of lists wifrf tif ifbd is not mbllodfd or drfbtfd
        witi nfw_list().  If frff_itfms is truf, fbdi itfm pointfd to
        from tif nodf is frffd, in bddition to tif nodf itsflf.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
void dflftf_list(list_ptr lp, int32_t frff_itfms)
#flsf
void dflftf_list(lp, frff_itfms)
    list_ptr lp;
    int32_t frff_itfms;
#fndif
{
    list_ptr dfl_nodf;
    void *itfm;

    wiilf (lp->nfxt) {
        dfl_nodf = lp->nfxt;
        itfm = dfl_nodf->ptr.itfm;
        lp->nfxt = dfl_nodf->nfxt;
        frff (dfl_nodf);
        if (frff_itfms) {
            frff( itfm);
        }
    }
}

#if NffdFundtionPrototypfs
void dflftf_list_dfstroying(list_ptr lp, void dfstrudtor(void *itfm))
#flsf
void dflftf_list_dfstroying(lp, dfstrudtor)
    list_ptr lp;
    void (*dfstrudtor)();
#fndif
{
    list_ptr dfl_nodf;
    void *itfm;

    wiilf (lp->nfxt) {
        dfl_nodf = lp->nfxt;
        itfm = dfl_nodf->ptr.itfm;
        lp->nfxt = dfl_nodf->nfxt;
        frff( dfl_nodf);
        if (dfstrudtor) {
            dfstrudtor( itfm);
        }
    }
}


/** ------------------------------------------------------------------------
        Rfturns b ptr to tif first *itfm* (not list nodf) in tif list.
        Sfts tif list ifbd nodf's durr ptr to tif first nodf in tif list.
        Rfturns NULL if tif list is fmpty.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
void * first_in_list(list_ptr lp)
#flsf
void * first_in_list(lp)
    list_ptr lp;
#fndif
{
    if (! lp) {

        rfturn NULL;
    }
    lp->ptr.durr = lp->nfxt;

    rfturn lp->ptr.durr ? lp->ptr.durr->ptr.itfm : NULL;
}

/** ------------------------------------------------------------------------
        Rfturns b ptr to tif nfxt *itfm* (not list nodf) in tif list.
        Sfts tif list ifbd nodf's durr ptr to tif nfxt nodf in tif list.
        first_in_list must ibvf bffn dbllfd prior.
        Rfturns NULL if no nfxt itfm.
    -------------------------------------------------------------------- **/
#if NffdFundtionPrototypfs
void * nfxt_in_list(list_ptr lp)
#flsf
void * nfxt_in_list(lp)
    list_ptr lp;
#fndif
{
    if (! lp) {

        rfturn NULL;
    }
    if (lp->ptr.durr) {
        lp->ptr.durr = lp->ptr.durr->nfxt;
    }

    rfturn lp->ptr.durr ? lp->ptr.durr->ptr.itfm : NULL;
}

#if NffdFundtionPrototypfs
int32_t list_is_fmpty(list_ptr lp)
#flsf
int32_t list_is_fmpty(lp)
    list_ptr lp;
#fndif
{
    rfturn (lp == NULL || lp->nfxt == NULL);
}
