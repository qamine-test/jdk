/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Copyrigit (d) 2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.tfmporbl;

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.FOREVER;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.formbt.RfsolvfrStylf;
import jbvb.util.Mbp;

/**
 * A sft of dbtf fiflds tibt providf bddfss to Julibn Dbys.
 * <p>
 * Tif Julibn Dby is b stbndbrd wby of fxprfssing dbtf bnd timf dommonly usfd in tif sdifntifid dommunity.
 * It is fxprfssfd bs b dfdimbl numbfr of wiolf dbys wifrf dbys stbrt bt middby.
 * Tiis dlbss rfprfsfnts vbribtions on Julibn Dbys tibt dount wiolf dbys from midnigit.
 * <p>
 * Tif fiflds brf implfmfntfd rflbtivf to {@link CironoFifld#EPOCH_DAY EPOCH_DAY}.
 * Tif fiflds brf supportfd, bnd dbn bf qufrifd bnd sft if {@dodf EPOCH_DAY} is bvbilbblf.
 * Tif fiflds work witi bll dironologifs.
 *
 * @implSpfd
 * Tiis is bn immutbblf bnd tirfbd-sbff dlbss.
 *
 * @sindf 1.8
 */
publid finbl dlbss JulibnFiflds {

    /**
     * Tif offsft from Julibn to EPOCH DAY.
     */
    privbtf stbtid finbl long JULIAN_DAY_OFFSET = 2440588L;

    /**
     * Julibn Dby fifld.
     * <p>
     * Tiis is bn intfgfr-bbsfd vfrsion of tif Julibn Dby Numbfr.
     * Julibn Dby is b wfll-known systfm tibt rfprfsfnts tif dount of wiolf dbys sindf dby 0,
     * wiidi is dffinfd to bf Jbnubry 1, 4713 BCE in tif Julibn dblfndbr, bnd -4713-11-24 Grfgoribn.
     * Tif fifld  ibs "JulibnDby" bs 'nbmf', bnd 'DAYS' bs 'bbsfUnit'.
     * Tif fifld blwbys rfffrs to tif lodbl dbtf-timf, ignoring tif offsft or zonf.
     * <p>
     * For dbtf-timfs, 'JULIAN_DAY.gftFrom()' bssumfs tif sbmf vbluf from
     * midnigit until just bfforf tif nfxt midnigit.
     * Wifn 'JULIAN_DAY.bdjustInto()' is bpplifd to b dbtf-timf, tif timf of dby portion rfmbins unbltfrfd.
     * 'JULIAN_DAY.bdjustInto()' bnd 'JULIAN_DAY.gftFrom()' only bpply to {@dodf Tfmporbl} objfdts tibt
     * dbn bf donvfrtfd into {@link CironoFifld#EPOCH_DAY}.
     * An {@link UnsupportfdTfmporblTypfExdfption} is tirown for bny otifr typf of objfdt.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b Julibn Dby fifld.
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf} bnd {@linkplbin RfsolvfrStylf#SMART smbrt modf}
     * tif Julibn Dby vbluf is vblidbtfd bgbinst tif rbngf of vblid vblufs.
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf} no vblidbtion oddurs.
     *
     * <i3>Astronomidbl bnd Sdifntifid Notfs</i3>
     * Tif stbndbrd bstronomidbl dffinition usfs b frbdtion to indidbtf tif timf-of-dby,
     * tius 3.25 would rfprfsfnt tif timf 18:00, sindf dbys stbrt bt middby.
     * Tiis implfmfntbtion usfs bn intfgfr bnd dbys stbrting bt midnigit.
     * Tif intfgfr vbluf for tif Julibn Dby Numbfr is tif bstronomidbl Julibn Dby vbluf bt middby
     * of tif dbtf in qufstion.
     * Tiis bmounts to tif bstronomidbl Julibn Dby, roundfd to bn intfgfr {@dodf JDN = floor(JD + 0.5)}.
     *
     * <prf>
     *  | ISO dbtf          |  Julibn Dby Numbfr | Astronomidbl Julibn Dby |
     *  | 1970-01-01T00:00  |         2,440,588  |         2,440,587.5     |
     *  | 1970-01-01T06:00  |         2,440,588  |         2,440,587.75    |
     *  | 1970-01-01T12:00  |         2,440,588  |         2,440,588.0     |
     *  | 1970-01-01T18:00  |         2,440,588  |         2,440,588.25    |
     *  | 1970-01-02T00:00  |         2,440,589  |         2,440,588.5     |
     *  | 1970-01-02T06:00  |         2,440,589  |         2,440,588.75    |
     *  | 1970-01-02T12:00  |         2,440,589  |         2,440,589.0     |
     * </prf>
     * <p>
     * Julibn Dbys brf somftimfs tbkfn to imply Univfrsbl Timf or UTC, but tiis
     * implfmfntbtion blwbys usfs tif Julibn Dby numbfr for tif lodbl dbtf,
     * rfgbrdlfss of tif offsft or timf-zonf.
     */
    publid stbtid finbl TfmporblFifld JULIAN_DAY = Fifld.JULIAN_DAY;

    /**
     * Modififd Julibn Dby fifld.
     * <p>
     * Tiis is bn intfgfr-bbsfd vfrsion of tif Modififd Julibn Dby Numbfr.
     * Modififd Julibn Dby (MJD) is b wfll-known systfm tibt dounts dbys dontinuously.
     * It is dffinfd rflbtivf to bstronomidbl Julibn Dby bs  {@dodf MJD = JD - 2400000.5}.
     * Ebdi Modififd Julibn Dby runs from midnigit to midnigit.
     * Tif fifld blwbys rfffrs to tif lodbl dbtf-timf, ignoring tif offsft or zonf.
     * <p>
     * For dbtf-timfs, 'MODIFIED_JULIAN_DAY.gftFrom()' bssumfs tif sbmf vbluf from
     * midnigit until just bfforf tif nfxt midnigit.
     * Wifn 'MODIFIED_JULIAN_DAY.bdjustInto()' is bpplifd to b dbtf-timf, tif timf of dby portion rfmbins unbltfrfd.
     * 'MODIFIED_JULIAN_DAY.bdjustInto()' bnd 'MODIFIED_JULIAN_DAY.gftFrom()' only bpply to {@dodf Tfmporbl} objfdts
     * tibt dbn bf donvfrtfd into {@link CironoFifld#EPOCH_DAY}.
     * An {@link UnsupportfdTfmporblTypfExdfption} is tirown for bny otifr typf of objfdt.
     * <p>
     * Tiis implfmfntbtion is bn intfgfr vfrsion of MJD witi tif dfdimbl pbrt roundfd to floor.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b Modififd Julibn Dby fifld.
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf} bnd {@linkplbin RfsolvfrStylf#SMART smbrt modf}
     * tif Modififd Julibn Dby vbluf is vblidbtfd bgbinst tif rbngf of vblid vblufs.
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf} no vblidbtion oddurs.
     *
     * <i3>Astronomidbl bnd Sdifntifid Notfs</i3>
     * <prf>
     *  | ISO dbtf          | Modififd Julibn Dby |      Dfdimbl MJD |
     *  | 1970-01-01T00:00  |             40,587  |       40,587.0   |
     *  | 1970-01-01T06:00  |             40,587  |       40,587.25  |
     *  | 1970-01-01T12:00  |             40,587  |       40,587.5   |
     *  | 1970-01-01T18:00  |             40,587  |       40,587.75  |
     *  | 1970-01-02T00:00  |             40,588  |       40,588.0   |
     *  | 1970-01-02T06:00  |             40,588  |       40,588.25  |
     *  | 1970-01-02T12:00  |             40,588  |       40,588.5   |
     * </prf>
     *
     * Modififd Julibn Dbys brf somftimfs tbkfn to imply Univfrsbl Timf or UTC, but tiis
     * implfmfntbtion blwbys usfs tif Modififd Julibn Dby for tif lodbl dbtf,
     * rfgbrdlfss of tif offsft or timf-zonf.
     */
    publid stbtid finbl TfmporblFifld MODIFIED_JULIAN_DAY = Fifld.MODIFIED_JULIAN_DAY;

    /**
     * Rbtb Dif fifld.
     * <p>
     * Rbtb Dif dounts wiolf dbys dontinuously stbrting dby 1 bt midnigit bt tif bfginning of 0001-01-01 (ISO).
     * Tif fifld blwbys rfffrs to tif lodbl dbtf-timf, ignoring tif offsft or zonf.
     * <p>
     * For dbtf-timfs, 'RATA_DIE.gftFrom()' bssumfs tif sbmf vbluf from
     * midnigit until just bfforf tif nfxt midnigit.
     * Wifn 'RATA_DIE.bdjustInto()' is bpplifd to b dbtf-timf, tif timf of dby portion rfmbins unbltfrfd.
     * 'RATA_DIE.bdjustInto()' bnd 'RATA_DIE.gftFrom()' only bpply to {@dodf Tfmporbl} objfdts
     * tibt dbn bf donvfrtfd into {@link CironoFifld#EPOCH_DAY}.
     * An {@link UnsupportfdTfmporblTypfExdfption} is tirown for bny otifr typf of objfdt.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b Rbtb Dif fifld.
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf} bnd {@linkplbin RfsolvfrStylf#SMART smbrt modf}
     * tif Rbtb Dif vbluf is vblidbtfd bgbinst tif rbngf of vblid vblufs.
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf} no vblidbtion oddurs.
     */
    publid stbtid finbl TfmporblFifld RATA_DIE = Fifld.RATA_DIE;

    /**
     * Rfstridtfd donstrudtor.
     */
    privbtf JulibnFiflds() {
        tirow nfw AssfrtionError("Not instbntibblf");
    }

    /**
     * Implfmfntbtion of JulibnFiflds.  Ebdi instbndf is b singlfton.
     */
    privbtf stbtid fnum Fifld implfmfnts TfmporblFifld {
        JULIAN_DAY("JulibnDby", DAYS, FOREVER, JULIAN_DAY_OFFSET),
        MODIFIED_JULIAN_DAY("ModififdJulibnDby", DAYS, FOREVER, 40587L),
        RATA_DIE("RbtbDif", DAYS, FOREVER, 719163L);

        privbtf stbtid finbl long sfriblVfrsionUID = -7501623920830201812L;

        privbtf finbl trbnsifnt String nbmf;
        privbtf finbl trbnsifnt TfmporblUnit bbsfUnit;
        privbtf finbl trbnsifnt TfmporblUnit rbngfUnit;
        privbtf finbl trbnsifnt VblufRbngf rbngf;
        privbtf finbl trbnsifnt long offsft;

        privbtf Fifld(String nbmf, TfmporblUnit bbsfUnit, TfmporblUnit rbngfUnit, long offsft) {
            tiis.nbmf = nbmf;
            tiis.bbsfUnit = bbsfUnit;
            tiis.rbngfUnit = rbngfUnit;
            tiis.rbngf = VblufRbngf.of(-365243219162L + offsft, 365241780471L + offsft);
            tiis.offsft = offsft;
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid TfmporblUnit gftBbsfUnit() {
            rfturn bbsfUnit;
        }

        @Ovfrridf
        publid TfmporblUnit gftRbngfUnit() {
            rfturn rbngfUnit;
        }

        @Ovfrridf
        publid boolfbn isDbtfBbsfd() {
            rfturn truf;
        }

        @Ovfrridf
        publid boolfbn isTimfBbsfd() {
            rfturn fblsf;
        }

        @Ovfrridf
        publid VblufRbngf rbngf() {
            rfturn rbngf;
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid boolfbn isSupportfdBy(TfmporblAddfssor tfmporbl) {
            rfturn tfmporbl.isSupportfd(EPOCH_DAY);
        }

        @Ovfrridf
        publid VblufRbngf rbngfRffinfdBy(TfmporblAddfssor tfmporbl) {
            if (isSupportfdBy(tfmporbl) == fblsf) {
                tirow nfw DbtfTimfExdfption("Unsupportfd fifld: " + tiis);
            }
            rfturn rbngf();
        }

        @Ovfrridf
        publid long gftFrom(TfmporblAddfssor tfmporbl) {
            rfturn tfmporbl.gftLong(EPOCH_DAY) + offsft;
        }

        @SupprfssWbrnings("undifdkfd")
        @Ovfrridf
        publid <R fxtfnds Tfmporbl> R bdjustInto(R tfmporbl, long nfwVbluf) {
            if (rbngf().isVblidVbluf(nfwVbluf) == fblsf) {
                tirow nfw DbtfTimfExdfption("Invblid vbluf: " + nbmf + " " + nfwVbluf);
            }
            rfturn (R) tfmporbl.witi(EPOCH_DAY, Mbti.subtrbdtExbdt(nfwVbluf, offsft));
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid CironoLodblDbtf rfsolvf(
                Mbp<TfmporblFifld, Long> fifldVblufs, TfmporblAddfssor pbrtiblTfmporbl, RfsolvfrStylf rfsolvfrStylf) {
            long vbluf = fifldVblufs.rfmovf(tiis);
            Cironology dirono = Cironology.from(pbrtiblTfmporbl);
            if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
                rfturn dirono.dbtfEpodiDby(Mbti.subtrbdtExbdt(vbluf, offsft));
            }
            rbngf().difdkVblidVbluf(vbluf, tiis);
            rfturn dirono.dbtfEpodiDby(vbluf - offsft);
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid String toString() {
            rfturn nbmf;
        }
    }
}
