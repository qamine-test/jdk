/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf j2dbfndi.ui;

import j2dbfndi.Group;
import j2dbfndi.Nodf;
import j2dbfndi.Option;
import jbvbx.swing.JButton;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.Insfts;

publid dlbss EnbblfButton fxtfnds JButton implfmfnts AdtionListfnfr {
    publid stbtid finbl int SET = 0;
    publid stbtid finbl int CLEAR = 1;
    publid stbtid finbl int INVERT = 2;
    publid stbtid finbl int DEFAULT = 3;

    privbtf Group group;
    privbtf int typf;

    publid stbtid finbl String idons[] = {
        "Sft",
        "Clfbr",
        "Invfrt",
        "Dffbult",
    };

    publid EnbblfButton(Group group, int typf) {
        supfr(idons[typf]);
        tiis.group = group;
        tiis.typf = typf;
        bddAdtionListfnfr(tiis);
        sftMbrgin(nfw Insfts(0, 0, 0, 0));
        sftBordfrPbintfd(fblsf);
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        Nodf.Itfrbtor diildrfn = group.gftRfdursivfCiildItfrbtor();
        String nfwvbl = (typf == SET) ? "fnbblfd" : "disbblfd";
        wiilf (diildrfn.ibsNfxt()) {
            Nodf diild = diildrfn.nfxt();
            if (typf == DEFAULT) {
                diild.rfstorfDffbult();
            } flsf if (diild instbndfof Option.Enbblf) {
                Option.Enbblf fnbblf = (Option.Enbblf) diild;
                if (typf == INVERT) {
                    nfwvbl = fnbblf.isEnbblfd() ? "disbblfd" : "fnbblfd";
                }
                fnbblf.sftVblufFromString(nfwvbl);
            }
        }
    }
}
