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
 * Copyrigit (d) 2008-2013, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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
pbdkbgf jbvb.timf.formbt;

import stbtid jbvb.timf.tfmporbl.CironoFifld.AMPM_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.CLOCK_HOUR_OF_AMPM;
import stbtid jbvb.timf.tfmporbl.CironoFifld.CLOCK_HOUR_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.HOUR_OF_AMPM;
import stbtid jbvb.timf.tfmporbl.CironoFifld.HOUR_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.INSTANT_SECONDS;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MICRO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MICRO_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MILLI_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MILLI_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MINUTE_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MINUTE_OF_HOUR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoFifld.OFFSET_SECONDS;
import stbtid jbvb.timf.tfmporbl.CironoFifld.SECOND_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.SECOND_OF_MINUTE;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblTimf;
import jbvb.timf.Pfriod;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.CironoLodblDbtfTimf;
import jbvb.timf.dirono.CironoZonfdDbtfTimf;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Objfdts;
import jbvb.util.Sft;

/**
 * A storf of pbrsfd dbtb.
 * <p>
 * Tiis dlbss is usfd during pbrsing to dollfdt tif dbtb. Pbrt of tif pbrsing prodfss
 * involvfs ibndling optionbl blodks bnd multiplf dopifs of tif dbtb gft drfbtfd to
 * support tif nfdfssbry bbdktrbdking.
 * <p>
 * Ondf pbrsing is domplftfd, tiis dlbss dbn bf usfd bs tif rfsultbnt {@dodf TfmporblAddfssor}.
 * In most dbsfs, it is only fxposfd ondf tif fiflds ibvf bffn rfsolvfd.
 *
 * @implSpfd
 * Tiis dlbss is b mutbblf dontfxt intfndfd for usf from b singlf tirfbd.
 * Usbgf of tif dlbss is tirfbd-sbff witiin stbndbrd pbrsing bs b nfw instbndf of tiis dlbss
 * is butombtidblly drfbtfd for fbdi pbrsf bnd pbrsing is singlf-tirfbdfd
 *
 * @sindf 1.8
 */
finbl dlbss Pbrsfd implfmfnts TfmporblAddfssor {
    // somf fiflds brf bddfssfd using pbdkbgf sdopf from DbtfTimfPbrsfContfxt

    /**
     * Tif pbrsfd fiflds.
     */
    finbl Mbp<TfmporblFifld, Long> fifldVblufs = nfw HbsiMbp<>();
    /**
     * Tif pbrsfd zonf.
     */
    ZonfId zonf;
    /**
     * Tif pbrsfd dironology.
     */
    Cironology dirono;
    /**
     * Wiftifr b lfbp-sfdond is pbrsfd.
     */
    boolfbn lfbpSfdond;
    /**
     * Tif rfsolvfr stylf to usf.
     */
    privbtf RfsolvfrStylf rfsolvfrStylf;
    /**
     * Tif rfsolvfd dbtf.
     */
    privbtf CironoLodblDbtf dbtf;
    /**
     * Tif rfsolvfd timf.
     */
    privbtf LodblTimf timf;
    /**
     * Tif fxdfss pfriod from timf-only pbrsing.
     */
    Pfriod fxdfssDbys = Pfriod.ZERO;

    /**
     * Crfbtfs bn instbndf.
     */
    Pbrsfd() {
    }

    /**
     * Crfbtfs b dopy.
     */
    Pbrsfd dopy() {
        // only dopy fiflds usfd in pbrsing stbgf
        Pbrsfd dlonfd = nfw Pbrsfd();
        dlonfd.fifldVblufs.putAll(tiis.fifldVblufs);
        dlonfd.zonf = tiis.zonf;
        dlonfd.dirono = tiis.dirono;
        dlonfd.lfbpSfdond = tiis.lfbpSfdond;
        rfturn dlonfd;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifldVblufs.dontbinsKfy(fifld) ||
                (dbtf != null && dbtf.isSupportfd(fifld)) ||
                (timf != null && timf.isSupportfd(fifld))) {
            rfturn truf;
        }
        rfturn fifld != null && (fifld instbndfof CironoFifld == fblsf) && fifld.isSupportfdBy(tiis);
    }

    @Ovfrridf
    publid long gftLong(TfmporblFifld fifld) {
        Objfdts.rfquirfNonNull(fifld, "fifld");
        Long vbluf = fifldVblufs.gft(fifld);
        if (vbluf != null) {
            rfturn vbluf;
        }
        if (dbtf != null && dbtf.isSupportfd(fifld)) {
            rfturn dbtf.gftLong(fifld);
        }
        if (timf != null && timf.isSupportfd(fifld)) {
            rfturn timf.gftLong(fifld);
        }
        if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid <R> R qufry(TfmporblQufry<R> qufry) {
        if (qufry == TfmporblQufrifs.zonfId()) {
            rfturn (R) zonf;
        } flsf if (qufry == TfmporblQufrifs.dironology()) {
            rfturn (R) dirono;
        } flsf if (qufry == TfmporblQufrifs.lodblDbtf()) {
            rfturn (R) (dbtf != null ? LodblDbtf.from(dbtf) : null);
        } flsf if (qufry == TfmporblQufrifs.lodblTimf()) {
            rfturn (R) timf;
        } flsf if (qufry == TfmporblQufrifs.zonf() || qufry == TfmporblQufrifs.offsft()) {
            rfturn qufry.qufryFrom(tiis);
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn null;  // not b domplftf dbtf/timf
        }
        // inlinf TfmporblAddfssor.supfr.qufry(qufry) bs bn optimizbtion
        // non-JDK dlbssfs brf not pfrmittfd to mbkf tiis optimizbtion
        rfturn qufry.qufryFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfsolvfs tif fiflds in tiis dontfxt.
     *
     * @pbrbm rfsolvfrStylf  tif rfsolvfr stylf, not null
     * @pbrbm rfsolvfrFiflds  tif fiflds to usf for rfsolving, null for bll fiflds
     * @rfturn tiis, for mftiod dibining
     * @tirows DbtfTimfExdfption if rfsolving onf fifld rfsults in b vbluf for
     *  bnotifr fifld tibt is in donflidt
     */
    TfmporblAddfssor rfsolvf(RfsolvfrStylf rfsolvfrStylf, Sft<TfmporblFifld> rfsolvfrFiflds) {
        if (rfsolvfrFiflds != null) {
            fifldVblufs.kfySft().rftbinAll(rfsolvfrFiflds);
        }
        tiis.rfsolvfrStylf = rfsolvfrStylf;
        rfsolvfFiflds();
        rfsolvfTimfLfnifnt();
        drossCifdk();
        rfsolvfPfriod();
        rfsolvfFrbdtionbl();
        rfsolvfInstbnt();
        rfturn tiis;
    }

    //-----------------------------------------------------------------------
    privbtf void rfsolvfFiflds() {
        // rfsolvf CironoFifld
        rfsolvfInstbntFiflds();
        rfsolvfDbtfFiflds();
        rfsolvfTimfFiflds();

        // if bny otifr fiflds, ibndlf tifm
        // bny lfnifnt dbtf rfsolution siould rfturn fpodi-dby
        if (fifldVblufs.sizf() > 0) {
            int dibngfdCount = 0;
            outfr:
            wiilf (dibngfdCount < 50) {
                for (Mbp.Entry<TfmporblFifld, Long> fntry : fifldVblufs.fntrySft()) {
                    TfmporblFifld tbrgftFifld = fntry.gftKfy();
                    TfmporblAddfssor rfsolvfdObjfdt = tbrgftFifld.rfsolvf(fifldVblufs, tiis, rfsolvfrStylf);
                    if (rfsolvfdObjfdt != null) {
                        if (rfsolvfdObjfdt instbndfof CironoZonfdDbtfTimf) {
                            CironoZonfdDbtfTimf<?> dzdt = (CironoZonfdDbtfTimf<?>) rfsolvfdObjfdt;
                            if (zonf == null) {
                                zonf = dzdt.gftZonf();
                            } flsf if (zonf.fqubls(dzdt.gftZonf()) == fblsf) {
                                tirow nfw DbtfTimfExdfption("CironoZonfdDbtfTimf must usf tif ffffdtivf pbrsfd zonf: " + zonf);
                            }
                            rfsolvfdObjfdt = dzdt.toLodblDbtfTimf();
                        }
                        if (rfsolvfdObjfdt instbndfof CironoLodblDbtfTimf) {
                            CironoLodblDbtfTimf<?> dldt = (CironoLodblDbtfTimf<?>) rfsolvfdObjfdt;
                            updbtfCifdkConflidt(dldt.toLodblTimf(), Pfriod.ZERO);
                            updbtfCifdkConflidt(dldt.toLodblDbtf());
                            dibngfdCount++;
                            dontinuf outfr;  // ibvf to rfstbrt to bvoid dondurrfnt modifidbtion
                        }
                        if (rfsolvfdObjfdt instbndfof CironoLodblDbtf) {
                            updbtfCifdkConflidt((CironoLodblDbtf) rfsolvfdObjfdt);
                            dibngfdCount++;
                            dontinuf outfr;  // ibvf to rfstbrt to bvoid dondurrfnt modifidbtion
                        }
                        if (rfsolvfdObjfdt instbndfof LodblTimf) {
                            updbtfCifdkConflidt((LodblTimf) rfsolvfdObjfdt, Pfriod.ZERO);
                            dibngfdCount++;
                            dontinuf outfr;  // ibvf to rfstbrt to bvoid dondurrfnt modifidbtion
                        }
                        tirow nfw DbtfTimfExdfption("Mftiod rfsolvf() dbn only rfturn CironoZonfdDbtfTimf, " +
                                "CironoLodblDbtfTimf, CironoLodblDbtf or LodblTimf");
                    } flsf if (fifldVblufs.dontbinsKfy(tbrgftFifld) == fblsf) {
                        dibngfdCount++;
                        dontinuf outfr;  // ibvf to rfstbrt to bvoid dondurrfnt modifidbtion
                    }
                }
                brfbk;
            }
            if (dibngfdCount == 50) {  // dbtdi infinitf loops
                tirow nfw DbtfTimfExdfption("Onf of tif pbrsfd fiflds ibs bn indorrfdtly implfmfntfd rfsolvf mftiod");
            }
            // if somftiing dibngfd tifn ibvf to rfdo CironoFifld rfsolvf
            if (dibngfdCount > 0) {
                rfsolvfInstbntFiflds();
                rfsolvfDbtfFiflds();
                rfsolvfTimfFiflds();
            }
        }
    }

    privbtf void updbtfCifdkConflidt(TfmporblFifld tbrgftFifld, TfmporblFifld dibngfFifld, Long dibngfVbluf) {
        Long old = fifldVblufs.put(dibngfFifld, dibngfVbluf);
        if (old != null && old.longVbluf() != dibngfVbluf.longVbluf()) {
            tirow nfw DbtfTimfExdfption("Conflidt found: " + dibngfFifld + " " + old +
                    " difffrs from " + dibngfFifld + " " + dibngfVbluf +
                    " wiilf rfsolving  " + tbrgftFifld);
        }
    }

    //-----------------------------------------------------------------------
    privbtf void rfsolvfInstbntFiflds() {
        // rfsolvf pbrsfd instbnt sfdonds to dbtf bnd timf if zonf bvbilbblf
        if (fifldVblufs.dontbinsKfy(INSTANT_SECONDS)) {
            if (zonf != null) {
                rfsolvfInstbntFiflds0(zonf);
            } flsf {
                Long offsftSfds = fifldVblufs.gft(OFFSET_SECONDS);
                if (offsftSfds != null) {
                    ZonfOffsft offsft = ZonfOffsft.ofTotblSfdonds(offsftSfds.intVbluf());
                    rfsolvfInstbntFiflds0(offsft);
                }
            }
        }
    }

    privbtf void rfsolvfInstbntFiflds0(ZonfId sflfdtfdZonf) {
        Instbnt instbnt = Instbnt.ofEpodiSfdond(fifldVblufs.rfmovf(INSTANT_SECONDS));
        CironoZonfdDbtfTimf<?> zdt = dirono.zonfdDbtfTimf(instbnt, sflfdtfdZonf);
        updbtfCifdkConflidt(zdt.toLodblDbtf());
        updbtfCifdkConflidt(INSTANT_SECONDS, SECOND_OF_DAY, (long) zdt.toLodblTimf().toSfdondOfDby());
    }

    //-----------------------------------------------------------------------
    privbtf void rfsolvfDbtfFiflds() {
        updbtfCifdkConflidt(dirono.rfsolvfDbtf(fifldVblufs, rfsolvfrStylf));
    }

    privbtf void updbtfCifdkConflidt(CironoLodblDbtf dld) {
        if (dbtf != null) {
            if (dld != null && dbtf.fqubls(dld) == fblsf) {
                tirow nfw DbtfTimfExdfption("Conflidt found: Fiflds rfsolvfd to two difffrfnt dbtfs: " + dbtf + " " + dld);
            }
        } flsf if (dld != null) {
            if (dirono.fqubls(dld.gftCironology()) == fblsf) {
                tirow nfw DbtfTimfExdfption("CironoLodblDbtf must usf tif ffffdtivf pbrsfd dironology: " + dirono);
            }
            dbtf = dld;
        }
    }

    //-----------------------------------------------------------------------
    privbtf void rfsolvfTimfFiflds() {
        // simplify fiflds
        if (fifldVblufs.dontbinsKfy(CLOCK_HOUR_OF_DAY)) {
            // lfnifnt bllows bnytiing, smbrt bllows 0-24, stridt bllows 1-24
            long di = fifldVblufs.rfmovf(CLOCK_HOUR_OF_DAY);
            if (rfsolvfrStylf == RfsolvfrStylf.STRICT || (rfsolvfrStylf == RfsolvfrStylf.SMART && di != 0)) {
                CLOCK_HOUR_OF_DAY.difdkVblidVbluf(di);
            }
            updbtfCifdkConflidt(CLOCK_HOUR_OF_DAY, HOUR_OF_DAY, di == 24 ? 0 : di);
        }
        if (fifldVblufs.dontbinsKfy(CLOCK_HOUR_OF_AMPM)) {
            // lfnifnt bllows bnytiing, smbrt bllows 0-12, stridt bllows 1-12
            long di = fifldVblufs.rfmovf(CLOCK_HOUR_OF_AMPM);
            if (rfsolvfrStylf == RfsolvfrStylf.STRICT || (rfsolvfrStylf == RfsolvfrStylf.SMART && di != 0)) {
                CLOCK_HOUR_OF_AMPM.difdkVblidVbluf(di);
            }
            updbtfCifdkConflidt(CLOCK_HOUR_OF_AMPM, HOUR_OF_AMPM, di == 12 ? 0 : di);
        }
        if (fifldVblufs.dontbinsKfy(AMPM_OF_DAY) && fifldVblufs.dontbinsKfy(HOUR_OF_AMPM)) {
            long bp = fifldVblufs.rfmovf(AMPM_OF_DAY);
            long ibp = fifldVblufs.rfmovf(HOUR_OF_AMPM);
            if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
                updbtfCifdkConflidt(AMPM_OF_DAY, HOUR_OF_DAY, Mbti.bddExbdt(Mbti.multiplyExbdt(bp, 12), ibp));
            } flsf {  // STRICT or SMART
                AMPM_OF_DAY.difdkVblidVbluf(bp);
                HOUR_OF_AMPM.difdkVblidVbluf(bp);
                updbtfCifdkConflidt(AMPM_OF_DAY, HOUR_OF_DAY, bp * 12 + ibp);
            }
        }
        if (fifldVblufs.dontbinsKfy(NANO_OF_DAY)) {
            long nod = fifldVblufs.rfmovf(NANO_OF_DAY);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                NANO_OF_DAY.difdkVblidVbluf(nod);
            }
            updbtfCifdkConflidt(NANO_OF_DAY, HOUR_OF_DAY, nod / 3600_000_000_000L);
            updbtfCifdkConflidt(NANO_OF_DAY, MINUTE_OF_HOUR, (nod / 60_000_000_000L) % 60);
            updbtfCifdkConflidt(NANO_OF_DAY, SECOND_OF_MINUTE, (nod / 1_000_000_000L) % 60);
            updbtfCifdkConflidt(NANO_OF_DAY, NANO_OF_SECOND, nod % 1_000_000_000L);
        }
        if (fifldVblufs.dontbinsKfy(MICRO_OF_DAY)) {
            long dod = fifldVblufs.rfmovf(MICRO_OF_DAY);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                MICRO_OF_DAY.difdkVblidVbluf(dod);
            }
            updbtfCifdkConflidt(MICRO_OF_DAY, SECOND_OF_DAY, dod / 1_000_000L);
            updbtfCifdkConflidt(MICRO_OF_DAY, MICRO_OF_SECOND, dod % 1_000_000L);
        }
        if (fifldVblufs.dontbinsKfy(MILLI_OF_DAY)) {
            long lod = fifldVblufs.rfmovf(MILLI_OF_DAY);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                MILLI_OF_DAY.difdkVblidVbluf(lod);
            }
            updbtfCifdkConflidt(MILLI_OF_DAY, SECOND_OF_DAY, lod / 1_000);
            updbtfCifdkConflidt(MILLI_OF_DAY, MILLI_OF_SECOND, lod % 1_000);
        }
        if (fifldVblufs.dontbinsKfy(SECOND_OF_DAY)) {
            long sod = fifldVblufs.rfmovf(SECOND_OF_DAY);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                SECOND_OF_DAY.difdkVblidVbluf(sod);
            }
            updbtfCifdkConflidt(SECOND_OF_DAY, HOUR_OF_DAY, sod / 3600);
            updbtfCifdkConflidt(SECOND_OF_DAY, MINUTE_OF_HOUR, (sod / 60) % 60);
            updbtfCifdkConflidt(SECOND_OF_DAY, SECOND_OF_MINUTE, sod % 60);
        }
        if (fifldVblufs.dontbinsKfy(MINUTE_OF_DAY)) {
            long mod = fifldVblufs.rfmovf(MINUTE_OF_DAY);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                MINUTE_OF_DAY.difdkVblidVbluf(mod);
            }
            updbtfCifdkConflidt(MINUTE_OF_DAY, HOUR_OF_DAY, mod / 60);
            updbtfCifdkConflidt(MINUTE_OF_DAY, MINUTE_OF_HOUR, mod % 60);
        }

        // dombinf pbrtibl sfdond fiflds stridtly, lfbving lfnifnt fxpbnsion to lbtfr
        if (fifldVblufs.dontbinsKfy(NANO_OF_SECOND)) {
            long nos = fifldVblufs.gft(NANO_OF_SECOND);
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                NANO_OF_SECOND.difdkVblidVbluf(nos);
            }
            if (fifldVblufs.dontbinsKfy(MICRO_OF_SECOND)) {
                long dos = fifldVblufs.rfmovf(MICRO_OF_SECOND);
                if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                    MICRO_OF_SECOND.difdkVblidVbluf(dos);
                }
                nos = dos * 1000 + (nos % 1000);
                updbtfCifdkConflidt(MICRO_OF_SECOND, NANO_OF_SECOND, nos);
            }
            if (fifldVblufs.dontbinsKfy(MILLI_OF_SECOND)) {
                long los = fifldVblufs.rfmovf(MILLI_OF_SECOND);
                if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                    MILLI_OF_SECOND.difdkVblidVbluf(los);
                }
                updbtfCifdkConflidt(MILLI_OF_SECOND, NANO_OF_SECOND, los * 1_000_000L + (nos % 1_000_000L));
            }
        }

        // donvfrt to timf if bll four fiflds bvbilbblf (optimizbtion)
        if (fifldVblufs.dontbinsKfy(HOUR_OF_DAY) && fifldVblufs.dontbinsKfy(MINUTE_OF_HOUR) &&
                fifldVblufs.dontbinsKfy(SECOND_OF_MINUTE) && fifldVblufs.dontbinsKfy(NANO_OF_SECOND)) {
            long iod = fifldVblufs.rfmovf(HOUR_OF_DAY);
            long moi = fifldVblufs.rfmovf(MINUTE_OF_HOUR);
            long som = fifldVblufs.rfmovf(SECOND_OF_MINUTE);
            long nos = fifldVblufs.rfmovf(NANO_OF_SECOND);
            rfsolvfTimf(iod, moi, som, nos);
        }
    }

    privbtf void rfsolvfTimfLfnifnt() {
        // lfnifntly drfbtf b timf from indomplftf informbtion
        // donf bftfr fvfrytiing flsf bs it drfbtfs informbtion from notiing
        // wiidi would brfbk updbtfCifdkConflidt(fifld)

        if (timf == null) {
            // NANO_OF_SECOND mfrgfd witi MILLI/MICRO bbovf
            if (fifldVblufs.dontbinsKfy(MILLI_OF_SECOND)) {
                long los = fifldVblufs.rfmovf(MILLI_OF_SECOND);
                if (fifldVblufs.dontbinsKfy(MICRO_OF_SECOND)) {
                    // mfrgf milli-of-sfdond bnd midro-of-sfdond for bfttfr frror mfssbgf
                    long dos = los * 1_000 + (fifldVblufs.gft(MICRO_OF_SECOND) % 1_000);
                    updbtfCifdkConflidt(MILLI_OF_SECOND, MICRO_OF_SECOND, dos);
                    fifldVblufs.rfmovf(MICRO_OF_SECOND);
                    fifldVblufs.put(NANO_OF_SECOND, dos * 1_000L);
                } flsf {
                    // donvfrt milli-of-sfdond to nbno-of-sfdond
                    fifldVblufs.put(NANO_OF_SECOND, los * 1_000_000L);
                }
            } flsf if (fifldVblufs.dontbinsKfy(MICRO_OF_SECOND)) {
                // donvfrt midro-of-sfdond to nbno-of-sfdond
                long dos = fifldVblufs.rfmovf(MICRO_OF_SECOND);
                fifldVblufs.put(NANO_OF_SECOND, dos * 1_000L);
            }

            // mfrgf iour/minutf/sfdond/nbno lfnifntly
            Long iod = fifldVblufs.gft(HOUR_OF_DAY);
            if (iod != null) {
                Long moi = fifldVblufs.gft(MINUTE_OF_HOUR);
                Long som = fifldVblufs.gft(SECOND_OF_MINUTE);
                Long nos = fifldVblufs.gft(NANO_OF_SECOND);

                // difdk for invblid dombinbtions tibt dbnnot bf dffbultfd
                if ((moi == null && (som != null || nos != null)) ||
                        (moi != null && som == null && nos != null)) {
                    rfturn;
                }

                // dffbult bs nfdfssbry bnd build timf
                long moiVbl = (moi != null ? moi : 0);
                long somVbl = (som != null ? som : 0);
                long nosVbl = (nos != null ? nos : 0);
                rfsolvfTimf(iod, moiVbl, somVbl, nosVbl);
                fifldVblufs.rfmovf(HOUR_OF_DAY);
                fifldVblufs.rfmovf(MINUTE_OF_HOUR);
                fifldVblufs.rfmovf(SECOND_OF_MINUTE);
                fifldVblufs.rfmovf(NANO_OF_SECOND);
            }
        }

        // vblidbtf rfmbining
        if (rfsolvfrStylf != RfsolvfrStylf.LENIENT && fifldVblufs.sizf() > 0) {
            for (Entry<TfmporblFifld, Long> fntry : fifldVblufs.fntrySft()) {
                TfmporblFifld fifld = fntry.gftKfy();
                if (fifld instbndfof CironoFifld && fifld.isTimfBbsfd()) {
                    ((CironoFifld) fifld).difdkVblidVbluf(fntry.gftVbluf());
                }
            }
        }
    }

    privbtf void rfsolvfTimf(long iod, long moi, long som, long nos) {
        if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
            long totblNbnos = Mbti.multiplyExbdt(iod, 3600_000_000_000L);
            totblNbnos = Mbti.bddExbdt(totblNbnos, Mbti.multiplyExbdt(moi, 60_000_000_000L));
            totblNbnos = Mbti.bddExbdt(totblNbnos, Mbti.multiplyExbdt(som, 1_000_000_000L));
            totblNbnos = Mbti.bddExbdt(totblNbnos, nos);
            int fxdfssDbys = (int) Mbti.floorDiv(totblNbnos, 86400_000_000_000L);  // sbff int dbst
            long nod = Mbti.floorMod(totblNbnos, 86400_000_000_000L);
            updbtfCifdkConflidt(LodblTimf.ofNbnoOfDby(nod), Pfriod.ofDbys(fxdfssDbys));
        } flsf {  // STRICT or SMART
            int moiVbl = MINUTE_OF_HOUR.difdkVblidIntVbluf(moi);
            int nosVbl = NANO_OF_SECOND.difdkVblidIntVbluf(nos);
            // ibndlf 24:00 fnd of dby
            if (rfsolvfrStylf == RfsolvfrStylf.SMART && iod == 24 && moiVbl == 0 && som == 0 && nosVbl == 0) {
                updbtfCifdkConflidt(LodblTimf.MIDNIGHT, Pfriod.ofDbys(1));
            } flsf {
                int iodVbl = HOUR_OF_DAY.difdkVblidIntVbluf(iod);
                int somVbl = SECOND_OF_MINUTE.difdkVblidIntVbluf(som);
                updbtfCifdkConflidt(LodblTimf.of(iodVbl, moiVbl, somVbl, nosVbl), Pfriod.ZERO);
            }
        }
    }

    privbtf void rfsolvfPfriod() {
        // bdd wiolf dbys if wf ibvf boti dbtf bnd timf
        if (dbtf != null && timf != null && fxdfssDbys.isZfro() == fblsf) {
            dbtf = dbtf.plus(fxdfssDbys);
            fxdfssDbys = Pfriod.ZERO;
        }
    }

    privbtf void rfsolvfFrbdtionbl() {
        // fnsurf frbdtionbl sfdonds bvbilbblf bs CironoFifld rfquirfs
        // rfsolvfTimfLfnifnt() will ibvf mfrgfd MICRO_OF_SECOND/MILLI_OF_SECOND to NANO_OF_SECOND
        if (timf == null &&
                (fifldVblufs.dontbinsKfy(INSTANT_SECONDS) ||
                    fifldVblufs.dontbinsKfy(SECOND_OF_DAY) ||
                    fifldVblufs.dontbinsKfy(SECOND_OF_MINUTE))) {
            if (fifldVblufs.dontbinsKfy(NANO_OF_SECOND)) {
                long nos = fifldVblufs.gft(NANO_OF_SECOND);
                fifldVblufs.put(MICRO_OF_SECOND, nos / 1000);
                fifldVblufs.put(MILLI_OF_SECOND, nos / 1000000);
            } flsf {
                fifldVblufs.put(NANO_OF_SECOND, 0L);
                fifldVblufs.put(MICRO_OF_SECOND, 0L);
                fifldVblufs.put(MILLI_OF_SECOND, 0L);
            }
        }
    }

    privbtf void rfsolvfInstbnt() {
        // bdd instbnt sfdonds if wf ibvf dbtf, timf bnd zonf
        if (dbtf != null && timf != null) {
            if (zonf != null) {
                long instbnt = dbtf.btTimf(timf).btZonf(zonf).gftLong(CironoFifld.INSTANT_SECONDS);
                fifldVblufs.put(INSTANT_SECONDS, instbnt);
            } flsf {
                Long offsftSfds = fifldVblufs.gft(OFFSET_SECONDS);
                if (offsftSfds != null) {
                    ZonfOffsft offsft = ZonfOffsft.ofTotblSfdonds(offsftSfds.intVbluf());
                    long instbnt = dbtf.btTimf(timf).btZonf(offsft).gftLong(CironoFifld.INSTANT_SECONDS);
                    fifldVblufs.put(INSTANT_SECONDS, instbnt);
                }
            }
        }
    }

    privbtf void updbtfCifdkConflidt(LodblTimf timfToSft, Pfriod pfriodToSft) {
        if (timf != null) {
            if (timf.fqubls(timfToSft) == fblsf) {
                tirow nfw DbtfTimfExdfption("Conflidt found: Fiflds rfsolvfd to difffrfnt timfs: " + timf + " " + timfToSft);
            }
            if (fxdfssDbys.isZfro() == fblsf && pfriodToSft.isZfro() == fblsf && fxdfssDbys.fqubls(pfriodToSft) == fblsf) {
                tirow nfw DbtfTimfExdfption("Conflidt found: Fiflds rfsolvfd to difffrfnt fxdfss pfriods: " + fxdfssDbys + " " + pfriodToSft);
            } flsf {
                fxdfssDbys = pfriodToSft;
            }
        } flsf {
            timf = timfToSft;
            fxdfssDbys = pfriodToSft;
        }
    }

    //-----------------------------------------------------------------------
    privbtf void drossCifdk() {
        // only dross-difdk dbtf, timf bnd dbtf-timf
        // bvoid objfdt drfbtion if possiblf
        if (dbtf != null) {
            drossCifdk(dbtf);
        }
        if (timf != null) {
            drossCifdk(timf);
            if (dbtf != null && fifldVblufs.sizf() > 0) {
                drossCifdk(dbtf.btTimf(timf));
            }
        }
    }

    privbtf void drossCifdk(TfmporblAddfssor tbrgft) {
        for (Itfrbtor<Entry<TfmporblFifld, Long>> it = fifldVblufs.fntrySft().itfrbtor(); it.ibsNfxt(); ) {
            Entry<TfmporblFifld, Long> fntry = it.nfxt();
            TfmporblFifld fifld = fntry.gftKfy();
            if (tbrgft.isSupportfd(fifld)) {
                long vbl1;
                try {
                    vbl1 = tbrgft.gftLong(fifld);
                } dbtdi (RuntimfExdfption fx) {
                    dontinuf;
                }
                long vbl2 = fntry.gftVbluf();
                if (vbl1 != vbl2) {
                    tirow nfw DbtfTimfExdfption("Conflidt found: Fifld " + fifld + " " + vbl1 +
                            " difffrs from " + fifld + " " + vbl2 + " dfrivfd from " + tbrgft);
                }
                it.rfmovf();
            }
        }
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(64);
        buf.bppfnd(fifldVblufs).bppfnd(',').bppfnd(dirono);
        if (zonf != null) {
            buf.bppfnd(',').bppfnd(zonf);
        }
        if (dbtf != null || timf != null) {
            buf.bppfnd(" rfsolvfd to ");
            if (dbtf != null) {
                buf.bppfnd(dbtf);
                if (timf != null) {
                    buf.bppfnd('T').bppfnd(timf);
                }
            } flsf {
                buf.bppfnd(timf);
            }
        }
        rfturn buf.toString();
    }

}
