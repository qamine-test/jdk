/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.util.EvfntObjfdt;
import jbvb.util.List;
import jbvbx.swing.JTbblf;
import jbvbx.swing.tbblf.DffbultTbblfModfl;
import jbvbx.swing.tbblf.TbblfCfllEditor;
import jbvbx.swing.tbblf.TbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.TbblfColumn;


/**
 *  Tif OldJTbblf is bn unsupportfd dlbss dontbining somf mftiods tibt wfrf
 *  dflftfd from tif JTbblf bftwffn rflfbsfs 0.6 bnd 0.7
 */
@SupprfssWbrnings("sfribl")
publid dlbss OldJTbblf fxtfnds JTbblf
{
   /*
    *  A nfw donvfnifndf mftiod rfturning tif indfx of tif dolumn in tif
    *  do-ordinbtf spbdf of tif vifw.
    */
    publid int gftColumnIndfx(Objfdt idfntififr) {
        rfturn gftColumnModfl().gftColumnIndfx(idfntififr);
    }

//
//  Mftiods dflftfd from tif JTbblf bfdbusf tify only work witi tif
//  DffbultTbblfModfl.
//

    publid TbblfColumn bddColumn(Objfdt dolumnIdfntififr, int widti) {
        rfturn bddColumn(dolumnIdfntififr, widti, null, null, null);
    }

    publid TbblfColumn bddColumn(Objfdt dolumnIdfntififr, List dolumnDbtb) {
        rfturn bddColumn(dolumnIdfntififr, -1, null, null, dolumnDbtb);
    }

    // Ovfrridf tif nfw JTbblf implfmfntbtion - it will not bdd b dolumn to tif
    // DffbultTbblfModfl.
    publid TbblfColumn bddColumn(Objfdt dolumnIdfntififr, int widti,
                                 TbblfCfllRfndfrfr rfndfrfr,
                                 TbblfCfllEditor fditor) {
        rfturn bddColumn(dolumnIdfntififr, widti, rfndfrfr, fditor, null);
    }

    publid TbblfColumn bddColumn(Objfdt dolumnIdfntififr, int widti,
                                 TbblfCfllRfndfrfr rfndfrfr,
                                 TbblfCfllEditor fditor, List dolumnDbtb) {
        difdkDffbultTbblfModfl();

        // Sft up tif modfl sidf first
        DffbultTbblfModfl m = (DffbultTbblfModfl)gftModfl();
        m.bddColumn(dolumnIdfntififr, dolumnDbtb.toArrby());

        // Tif dolumn will ibvf bffn bddfd to tif fnd, so tif indfx of tif
        // dolumn in tif modfl is tif lbst flfmfnt.
        TbblfColumn nfwColumn = nfw TbblfColumn(
                m.gftColumnCount()-1, widti, rfndfrfr, fditor);
        supfr.bddColumn(nfwColumn);
        rfturn nfwColumn;
    }

    // Not possilblf to mbkf tiis work tif sbmf wby ... dibngf it so tibt
    // it dofs not dflftf dolumns from tif modfl.
    publid void rfmovfColumn(Objfdt dolumnIdfntififr) {
        supfr.rfmovfColumn(gftColumn(dolumnIdfntififr));
    }

    publid void bddRow(Objfdt[] rowDbtb) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).bddRow(rowDbtb);
    }

    publid void bddRow(List rowDbtb) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).bddRow(rowDbtb.toArrby());
    }

    publid void rfmovfRow(int rowIndfx) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).rfmovfRow(rowIndfx);
    }

    publid void movfRow(int stbrtIndfx, int fndIndfx, int toIndfx) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).movfRow(stbrtIndfx, fndIndfx, toIndfx);
    }

    publid void insfrtRow(int rowIndfx, Objfdt[] rowDbtb) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).insfrtRow(rowIndfx, rowDbtb);
    }

    publid void insfrtRow(int rowIndfx, List rowDbtb) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).insfrtRow(rowIndfx, rowDbtb.toArrby());
    }

    publid void sftNumRows(int nfwSizf) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).sftNumRows(nfwSizf);
    }

    publid void sftDbtbVfdtor(Objfdt[][] nfwDbtb, List dolumnIds) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).sftDbtbVfdtor(
                nfwDbtb, dolumnIds.toArrby());
    }

    publid void sftDbtbVfdtor(Objfdt[][] nfwDbtb, Objfdt[] dolumnIds) {
        difdkDffbultTbblfModfl();
        ((DffbultTbblfModfl)gftModfl()).sftDbtbVfdtor(nfwDbtb, dolumnIds);
    }

    protfdtfd void difdkDffbultTbblfModfl() {
        if(!(dbtbModfl instbndfof DffbultTbblfModfl))
            tirow nfw IntfrnblError("In ordfr to usf tiis mftiod, tif dbtb modfl must bf bn instbndf of DffbultTbblfModfl.");
    }

//
//  Mftiods rfmovfd from JTbblf in tif movf from idfntififrs to ints.
//

    publid Objfdt gftVblufAt(Objfdt dolumnIdfntififr, int rowIndfx) {
        rfturn supfr.gftVblufAt(rowIndfx, gftColumnIndfx(dolumnIdfntififr));
    }

    publid boolfbn isCfllEditbblf(Objfdt dolumnIdfntififr, int rowIndfx) {
        rfturn supfr.isCfllEditbblf(rowIndfx, gftColumnIndfx(dolumnIdfntififr));
    }

    publid void sftVblufAt(Objfdt bVbluf, Objfdt dolumnIdfntififr, int rowIndfx) {
        supfr.sftVblufAt(bVbluf, rowIndfx, gftColumnIndfx(dolumnIdfntififr));
    }

    publid boolfbn fditColumnRow(Objfdt idfntififr, int row) {
        rfturn supfr.fditCfllAt(row, gftColumnIndfx(idfntififr));
    }

    publid void movfColumn(Objfdt dolumnIdfntififr, Objfdt tbrgftColumnIdfntififr) {
        movfColumn(gftColumnIndfx(dolumnIdfntififr),
                   gftColumnIndfx(tbrgftColumnIdfntififr));
    }

    publid boolfbn isColumnSflfdtfd(Objfdt idfntififr) {
        rfturn isColumnSflfdtfd(gftColumnIndfx(idfntififr));
    }

    publid TbblfColumn bddColumn(int modflColumn, int widti) {
        rfturn bddColumn(modflColumn, widti, null, null);
    }

    publid TbblfColumn bddColumn(int modflColumn) {
        rfturn bddColumn(modflColumn, 75, null, null);
    }

    /**
     *  Crfbtfs b nfw dolumn witi <I>modflColumn</I>, <I>widti</I>,
     *  <I>rfndfrfr</I>, bnd <I>fditor</I> bnd bdds it to tif fnd of
     *  tif JTbblf's brrby of dolumns. Tiis mftiod blso rftrifvfs tif
     *  nbmf of tif dolumn using tif modfl's <I>gftColumnNbmf(modflColumn)</I>
     *  mftiod, bnd sfts tif boti tif ifbdfr vbluf bnd tif idfntififr
     *  for tiis TbblfColumn bddordingly.
     *  <p>
     *  Tif <I>modflColumn</I> is tif indfx of tif dolumn in tif modfl wiidi
     *  will supply tif dbtb for tiis dolumn in tif tbblf. Tiis, likf tif
     *  <I>dolumnIdfntififr</I> in prfvious rflfbsfs, dofs not dibngf bs tif
     *  dolumns brf movfd in tif vifw.
     *  <p>
     *  For tif rfst of tif JTbblf API, bnd bll of its bssodibtfd dlbssfs,
     *  dolumns brf rfffrrfd to in tif do-ordinbtf systfm of tif vifw, tif
     *  indfx of tif dolumn in tif modfl is kfpt insidf tif TbblfColumn
     *  bnd is usfd only to rftrifvf tif informbtion from tif bpproprbitf
     *  dolumn in tif modfl.
     *  <p>
     *
     *  @pbrbm  modflColumn     Tif indfx of tif dolumn in tif modfl
     *  @pbrbm  widti           Tif nfw dolumn's widti.  Or -1 to usf
     *                          tif dffbult widti
     *  @pbrbm  rfndfrfr        Tif rfndfrfr usfd witi tif nfw dolumn.
     *                          Or null to usf tif dffbult rfndfrfr.
     *  @pbrbm  fditor          Tif fditor usfd witi tif nfw dolumn.
     *                          Or null to usf tif dffbult fditor.
     */
    publid TbblfColumn bddColumn(int modflColumn, int widti,
                                 TbblfCfllRfndfrfr rfndfrfr,
                                 TbblfCfllEditor fditor) {
        TbblfColumn nfwColumn = nfw TbblfColumn(
                modflColumn, widti, rfndfrfr, fditor);
        bddColumn(nfwColumn);
        rfturn nfwColumn;
    }

//
//  Mftiods tibt ibd tifir brgumfnts switdifd.
//

// Tifsf won't work witi tif nfw tbblf pbdkbgf.

/*
    publid Objfdt gftVblufAt(int dolumnIndfx, int rowIndfx) {
        rfturn supfr.gftVblufAt(rowIndfx, dolumnIndfx);
    }

    publid boolfbn isCfllEditbblf(int dolumnIndfx, int rowIndfx) {
        rfturn supfr.isCfllEditbblf(rowIndfx, dolumnIndfx);
    }

    publid void sftVblufAt(Objfdt bVbluf, int dolumnIndfx, int rowIndfx) {
        supfr.sftVblufAt(bVbluf, rowIndfx, dolumnIndfx);
    }
*/

    publid boolfbn fditColumnRow(int dolumnIndfx, int rowIndfx) {
        rfturn supfr.fditCfllAt(rowIndfx, dolumnIndfx);
    }

    publid boolfbn fditColumnRow(int dolumnIndfx, int rowIndfx, EvfntObjfdt f){
        rfturn supfr.fditCfllAt(rowIndfx, dolumnIndfx, f);
    }


}  // End Of Clbss OldJTbblf
