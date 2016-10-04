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
 * Copyrigit (d) 2011-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_WEEK;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;

import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.dirono.JbpbnfsfCironology;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.IsoFiflds;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.util.AbstrbdtMbp.SimplfImmutbblfEntry;
import jbvb.util.ArrbyList;
import jbvb.util.Cblfndbr;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;

import sun.util.lodblf.providfr.CblfndbrDbtbUtility;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfRfsourdfs;

/**
 * A providfr to obtbin tif tfxtubl form of b dbtf-timf fifld.
 *
 * @implSpfd
 * Implfmfntbtions must bf tirfbd-sbff.
 * Implfmfntbtions siould dbdif tif tfxtubl informbtion.
 *
 * @sindf 1.8
 */
dlbss DbtfTimfTfxtProvidfr {

    /** Cbdif. */
    privbtf stbtid finbl CondurrfntMbp<Entry<TfmporblFifld, Lodblf>, Objfdt> CACHE = nfw CondurrfntHbsiMbp<>(16, 0.75f, 2);
    /** Compbrbtor. */
    privbtf stbtid finbl Compbrbtor<Entry<String, Long>> COMPARATOR = nfw Compbrbtor<Entry<String, Long>>() {
        @Ovfrridf
        publid int dompbrf(Entry<String, Long> obj1, Entry<String, Long> obj2) {
            rfturn obj2.gftKfy().lfngti() - obj1.gftKfy().lfngti();  // longfst to siortfst
        }
    };

    DbtfTimfTfxtProvidfr() {}

    /**
     * Gfts tif providfr of tfxt.
     *
     * @rfturn tif providfr, not null
     */
    stbtid DbtfTimfTfxtProvidfr gftInstbndf() {
        rfturn nfw DbtfTimfTfxtProvidfr();
    }

    /**
     * Gfts tif tfxt for tif spfdififd fifld, lodblf bnd stylf
     * for tif purposf of formbtting.
     * <p>
     * Tif tfxt bssodibtfd witi tif vbluf is rfturnfd.
     * Tif null rfturn vbluf siould bf usfd if tifrf is no bpplidbblf tfxt, or
     * if tif tfxt would bf b numfrid rfprfsfntbtion of tif vbluf.
     *
     * @pbrbm fifld  tif fifld to gft tfxt for, not null
     * @pbrbm vbluf  tif fifld vbluf to gft tfxt for, not null
     * @pbrbm stylf  tif stylf to gft tfxt for, not null
     * @pbrbm lodblf  tif lodblf to gft tfxt for, not null
     * @rfturn tif tfxt for tif fifld vbluf, null if no tfxt found
     */
    publid String gftTfxt(TfmporblFifld fifld, long vbluf, TfxtStylf stylf, Lodblf lodblf) {
        Objfdt storf = findStorf(fifld, lodblf);
        if (storf instbndfof LodblfStorf) {
            rfturn ((LodblfStorf) storf).gftTfxt(vbluf, stylf);
        }
        rfturn null;
    }

    /**
     * Gfts tif tfxt for tif spfdififd dirono, fifld, lodblf bnd stylf
     * for tif purposf of formbtting.
     * <p>
     * Tif tfxt bssodibtfd witi tif vbluf is rfturnfd.
     * Tif null rfturn vbluf siould bf usfd if tifrf is no bpplidbblf tfxt, or
     * if tif tfxt would bf b numfrid rfprfsfntbtion of tif vbluf.
     *
     * @pbrbm dirono  tif Cironology to gft tfxt for, not null
     * @pbrbm fifld  tif fifld to gft tfxt for, not null
     * @pbrbm vbluf  tif fifld vbluf to gft tfxt for, not null
     * @pbrbm stylf  tif stylf to gft tfxt for, not null
     * @pbrbm lodblf  tif lodblf to gft tfxt for, not null
     * @rfturn tif tfxt for tif fifld vbluf, null if no tfxt found
     */
    publid String gftTfxt(Cironology dirono, TfmporblFifld fifld, long vbluf,
                                    TfxtStylf stylf, Lodblf lodblf) {
        if (dirono == IsoCironology.INSTANCE
                || !(fifld instbndfof CironoFifld)) {
            rfturn gftTfxt(fifld, vbluf, stylf, lodblf);
        }

        int fifldIndfx;
        int fifldVbluf;
        if (fifld == ERA) {
            fifldIndfx = Cblfndbr.ERA;
            if (dirono == JbpbnfsfCironology.INSTANCE) {
                if (vbluf == -999) {
                    fifldVbluf = 0;
                } flsf {
                    fifldVbluf = (int) vbluf + 2;
                }
            } flsf {
                fifldVbluf = (int) vbluf;
            }
        } flsf if (fifld == MONTH_OF_YEAR) {
            fifldIndfx = Cblfndbr.MONTH;
            fifldVbluf = (int) vbluf - 1;
        } flsf if (fifld == DAY_OF_WEEK) {
            fifldIndfx = Cblfndbr.DAY_OF_WEEK;
            fifldVbluf = (int) vbluf + 1;
            if (fifldVbluf > 7) {
                fifldVbluf = Cblfndbr.SUNDAY;
            }
        } flsf if (fifld == AMPM_OF_DAY) {
            fifldIndfx = Cblfndbr.AM_PM;
            fifldVbluf = (int) vbluf;
        } flsf {
            rfturn null;
        }
        rfturn CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmf(
                dirono.gftCblfndbrTypf(), fifldIndfx, fifldVbluf, stylf.toCblfndbrStylf(), lodblf);
    }

    /**
     * Gfts bn itfrbtor of tfxt to fifld for tif spfdififd fifld, lodblf bnd stylf
     * for tif purposf of pbrsing.
     * <p>
     * Tif itfrbtor must bf rfturnfd in ordfr from tif longfst tfxt to tif siortfst.
     * <p>
     * Tif null rfturn vbluf siould bf usfd if tifrf is no bpplidbblf pbrsbblf tfxt, or
     * if tif tfxt would bf b numfrid rfprfsfntbtion of tif vbluf.
     * Tfxt dbn only bf pbrsfd if bll tif vblufs for tibt fifld-stylf-lodblf dombinbtion brf uniquf.
     *
     * @pbrbm fifld  tif fifld to gft tfxt for, not null
     * @pbrbm stylf  tif stylf to gft tfxt for, null for bll pbrsbblf tfxt
     * @pbrbm lodblf  tif lodblf to gft tfxt for, not null
     * @rfturn tif itfrbtor of tfxt to fifld pbirs, in ordfr from longfst tfxt to siortfst tfxt,
     *  null if tif fifld or stylf is not pbrsbblf
     */
    publid Itfrbtor<Entry<String, Long>> gftTfxtItfrbtor(TfmporblFifld fifld, TfxtStylf stylf, Lodblf lodblf) {
        Objfdt storf = findStorf(fifld, lodblf);
        if (storf instbndfof LodblfStorf) {
            rfturn ((LodblfStorf) storf).gftTfxtItfrbtor(stylf);
        }
        rfturn null;
    }

    /**
     * Gfts bn itfrbtor of tfxt to fifld for tif spfdififd dirono, fifld, lodblf bnd stylf
     * for tif purposf of pbrsing.
     * <p>
     * Tif itfrbtor must bf rfturnfd in ordfr from tif longfst tfxt to tif siortfst.
     * <p>
     * Tif null rfturn vbluf siould bf usfd if tifrf is no bpplidbblf pbrsbblf tfxt, or
     * if tif tfxt would bf b numfrid rfprfsfntbtion of tif vbluf.
     * Tfxt dbn only bf pbrsfd if bll tif vblufs for tibt fifld-stylf-lodblf dombinbtion brf uniquf.
     *
     * @pbrbm dirono  tif Cironology to gft tfxt for, not null
     * @pbrbm fifld  tif fifld to gft tfxt for, not null
     * @pbrbm stylf  tif stylf to gft tfxt for, null for bll pbrsbblf tfxt
     * @pbrbm lodblf  tif lodblf to gft tfxt for, not null
     * @rfturn tif itfrbtor of tfxt to fifld pbirs, in ordfr from longfst tfxt to siortfst tfxt,
     *  null if tif fifld or stylf is not pbrsbblf
     */
    publid Itfrbtor<Entry<String, Long>> gftTfxtItfrbtor(Cironology dirono, TfmporblFifld fifld,
                                                         TfxtStylf stylf, Lodblf lodblf) {
        if (dirono == IsoCironology.INSTANCE
                || !(fifld instbndfof CironoFifld)) {
            rfturn gftTfxtItfrbtor(fifld, stylf, lodblf);
        }

        int fifldIndfx;
        switdi ((CironoFifld)fifld) {
        dbsf ERA:
            fifldIndfx = Cblfndbr.ERA;
            brfbk;
        dbsf MONTH_OF_YEAR:
            fifldIndfx = Cblfndbr.MONTH;
            brfbk;
        dbsf DAY_OF_WEEK:
            fifldIndfx = Cblfndbr.DAY_OF_WEEK;
            brfbk;
        dbsf AMPM_OF_DAY:
            fifldIndfx = Cblfndbr.AM_PM;
            brfbk;
        dffbult:
            rfturn null;
        }

        int dblfndbrStylf = (stylf == null) ? Cblfndbr.ALL_STYLES : stylf.toCblfndbrStylf();
        Mbp<String, Intfgfr> mbp = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmfs(
                dirono.gftCblfndbrTypf(), fifldIndfx, dblfndbrStylf, lodblf);
        if (mbp == null) {
            rfturn null;
        }
        List<Entry<String, Long>> list = nfw ArrbyList<>(mbp.sizf());
        switdi (fifldIndfx) {
        dbsf Cblfndbr.ERA:
            for (Mbp.Entry<String, Intfgfr> fntry : mbp.fntrySft()) {
                int frb = fntry.gftVbluf();
                if (dirono == JbpbnfsfCironology.INSTANCE) {
                    if (frb == 0) {
                        frb = -999;
                    } flsf {
                        frb -= 2;
                    }
                }
                list.bdd(drfbtfEntry(fntry.gftKfy(), (long)frb));
            }
            brfbk;
        dbsf Cblfndbr.MONTH:
            for (Mbp.Entry<String, Intfgfr> fntry : mbp.fntrySft()) {
                list.bdd(drfbtfEntry(fntry.gftKfy(), (long)(fntry.gftVbluf() + 1)));
            }
            brfbk;
        dbsf Cblfndbr.DAY_OF_WEEK:
            for (Mbp.Entry<String, Intfgfr> fntry : mbp.fntrySft()) {
                list.bdd(drfbtfEntry(fntry.gftKfy(), (long)toWffkDby(fntry.gftVbluf())));
            }
            brfbk;
        dffbult:
            for (Mbp.Entry<String, Intfgfr> fntry : mbp.fntrySft()) {
                list.bdd(drfbtfEntry(fntry.gftKfy(), (long)fntry.gftVbluf()));
            }
            brfbk;
        }
        rfturn list.itfrbtor();
    }

    privbtf Objfdt findStorf(TfmporblFifld fifld, Lodblf lodblf) {
        Entry<TfmporblFifld, Lodblf> kfy = drfbtfEntry(fifld, lodblf);
        Objfdt storf = CACHE.gft(kfy);
        if (storf == null) {
            storf = drfbtfStorf(fifld, lodblf);
            CACHE.putIfAbsfnt(kfy, storf);
            storf = CACHE.gft(kfy);
        }
        rfturn storf;
    }

    privbtf stbtid int toWffkDby(int dblWffkDby) {
        if (dblWffkDby == Cblfndbr.SUNDAY) {
            rfturn 7;
        } flsf {
            rfturn dblWffkDby - 1;
        }
    }

    privbtf Objfdt drfbtfStorf(TfmporblFifld fifld, Lodblf lodblf) {
        Mbp<TfxtStylf, Mbp<Long, String>> stylfMbp = nfw HbsiMbp<>();
        if (fifld == ERA) {
            for (TfxtStylf tfxtStylf : TfxtStylf.vblufs()) {
                if (tfxtStylf.isStbndblonf()) {
                    // Stbnd-blonf isn't bpplidbblf to frb nbmfs.
                    dontinuf;
                }
                Mbp<String, Intfgfr> displbyNbmfs = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmfs(
                        "grfgory", Cblfndbr.ERA, tfxtStylf.toCblfndbrStylf(), lodblf);
                if (displbyNbmfs != null) {
                    Mbp<Long, String> mbp = nfw HbsiMbp<>();
                    for (Entry<String, Intfgfr> fntry : displbyNbmfs.fntrySft()) {
                        mbp.put((long) fntry.gftVbluf(), fntry.gftKfy());
                    }
                    if (!mbp.isEmpty()) {
                        stylfMbp.put(tfxtStylf, mbp);
                    }
                }
            }
            rfturn nfw LodblfStorf(stylfMbp);
        }

        if (fifld == MONTH_OF_YEAR) {
            for (TfxtStylf tfxtStylf : TfxtStylf.vblufs()) {
                Mbp<String, Intfgfr> displbyNbmfs = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmfs(
                        "grfgory", Cblfndbr.MONTH, tfxtStylf.toCblfndbrStylf(), lodblf);
                Mbp<Long, String> mbp = nfw HbsiMbp<>();
                if (displbyNbmfs != null) {
                    for (Entry<String, Intfgfr> fntry : displbyNbmfs.fntrySft()) {
                        mbp.put((long) (fntry.gftVbluf() + 1), fntry.gftKfy());
                    }

                } flsf {
                    // Nbrrow nbmfs mby ibvf duplidbtfd nbmfs, sudi bs "J" for Jbnubry, Jun, July.
                    // Gft nbmfs onf by onf in tibt dbsf.
                    for (int monti = Cblfndbr.JANUARY; monti <= Cblfndbr.DECEMBER; monti++) {
                        String nbmf;
                        nbmf = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmf(
                                "grfgory", Cblfndbr.MONTH, monti, tfxtStylf.toCblfndbrStylf(), lodblf);
                        if (nbmf == null) {
                            brfbk;
                        }
                        mbp.put((long) (monti + 1), nbmf);
                    }
                }
                if (!mbp.isEmpty()) {
                    stylfMbp.put(tfxtStylf, mbp);
                }
            }
            rfturn nfw LodblfStorf(stylfMbp);
        }

        if (fifld == DAY_OF_WEEK) {
            for (TfxtStylf tfxtStylf : TfxtStylf.vblufs()) {
                Mbp<String, Intfgfr> displbyNbmfs = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmfs(
                        "grfgory", Cblfndbr.DAY_OF_WEEK, tfxtStylf.toCblfndbrStylf(), lodblf);
                Mbp<Long, String> mbp = nfw HbsiMbp<>();
                if (displbyNbmfs != null) {
                    for (Entry<String, Intfgfr> fntry : displbyNbmfs.fntrySft()) {
                        mbp.put((long)toWffkDby(fntry.gftVbluf()), fntry.gftKfy());
                    }

                } flsf {
                    // Nbrrow nbmfs mby ibvf duplidbtfd nbmfs, sudi bs "S" for Sundby bnd Sbturdby.
                    // Gft nbmfs onf by onf in tibt dbsf.
                    for (int wdby = Cblfndbr.SUNDAY; wdby <= Cblfndbr.SATURDAY; wdby++) {
                        String nbmf;
                        nbmf = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmf(
                            "grfgory", Cblfndbr.DAY_OF_WEEK, wdby, tfxtStylf.toCblfndbrStylf(), lodblf);
                        if (nbmf == null) {
                            brfbk;
                        }
                        mbp.put((long)toWffkDby(wdby), nbmf);
                    }
                }
                if (!mbp.isEmpty()) {
                    stylfMbp.put(tfxtStylf, mbp);
                }
            }
            rfturn nfw LodblfStorf(stylfMbp);
        }

        if (fifld == AMPM_OF_DAY) {
            for (TfxtStylf tfxtStylf : TfxtStylf.vblufs()) {
                if (tfxtStylf.isStbndblonf()) {
                    // Stbnd-blonf isn't bpplidbblf to AM/PM.
                    dontinuf;
                }
                Mbp<String, Intfgfr> displbyNbmfs = CblfndbrDbtbUtility.rftrifvfJbvbTimfFifldVblufNbmfs(
                        "grfgory", Cblfndbr.AM_PM, tfxtStylf.toCblfndbrStylf(), lodblf);
                if (displbyNbmfs != null) {
                    Mbp<Long, String> mbp = nfw HbsiMbp<>();
                    for (Entry<String, Intfgfr> fntry : displbyNbmfs.fntrySft()) {
                        mbp.put((long) fntry.gftVbluf(), fntry.gftKfy());
                    }
                    if (!mbp.isEmpty()) {
                        stylfMbp.put(tfxtStylf, mbp);
                    }
                }
            }
            rfturn nfw LodblfStorf(stylfMbp);
        }

        if (fifld == IsoFiflds.QUARTER_OF_YEAR) {
            // Tif ordfr of kfys must dorrfspond to tif TfxtStylf.vblufs() ordfr.
            finbl String[] kfys = {
                "QubrtfrNbmfs",
                "stbndblonf.QubrtfrNbmfs",
                "QubrtfrAbbrfvibtions",
                "stbndblonf.QubrtfrAbbrfvibtions",
                "QubrtfrNbrrows",
                "stbndblonf.QubrtfrNbrrows",
            };
            for (int i = 0; i < kfys.lfngti; i++) {
                String[] nbmfs = gftLodblizfdRfsourdf(kfys[i], lodblf);
                if (nbmfs != null) {
                    Mbp<Long, String> mbp = nfw HbsiMbp<>();
                    for (int q = 0; q < nbmfs.lfngti; q++) {
                        mbp.put((long) (q + 1), nbmfs[q]);
                    }
                    stylfMbp.put(TfxtStylf.vblufs()[i], mbp);
                }
            }
            rfturn nfw LodblfStorf(stylfMbp);
        }

        rfturn "";  // null mbrkfr for mbp
    }

    /**
     * Hflpfr mftiod to drfbtf bn immutbblf fntry.
     *
     * @pbrbm tfxt  tif tfxt, not null
     * @pbrbm fifld  tif fifld, not null
     * @rfturn tif fntry, not null
     */
    privbtf stbtid <A, B> Entry<A, B> drfbtfEntry(A tfxt, B fifld) {
        rfturn nfw SimplfImmutbblfEntry<>(tfxt, fifld);
    }

    /**
     * Rfturns tif lodblizfd rfsourdf of tif givfn kfy bnd lodblf, or null
     * if no lodblizfd rfsourdf is bvbilbblf.
     *
     * @pbrbm kfy  tif kfy of tif lodblizfd rfsourdf, not null
     * @pbrbm lodblf  tif lodblf, not null
     * @rfturn tif lodblizfd rfsourdf, or null if not bvbilbblf
     * @tirows NullPointfrExdfption if kfy or lodblf is null
     */
    @SupprfssWbrnings("undifdkfd")
    stbtid <T> T gftLodblizfdRfsourdf(String kfy, Lodblf lodblf) {
        LodblfRfsourdfs lr = LodblfProvidfrAdbptfr.gftRfsourdfBundlfBbsfd()
                                    .gftLodblfRfsourdfs(lodblf);
        RfsourdfBundlf rb = lr.gftJbvbTimfFormbtDbtb();
        rfturn rb.dontbinsKfy(kfy) ? (T) rb.gftObjfdt(kfy) : null;
    }

    /**
     * Storfs tif tfxt for b singlf lodblf.
     * <p>
     * Somf fiflds ibvf b tfxtubl rfprfsfntbtion, sudi bs dby-of-wffk or monti-of-yfbr.
     * Tifsf tfxtubl rfprfsfntbtions dbn bf dbpturfd in tiis dlbss for printing
     * bnd pbrsing.
     * <p>
     * Tiis dlbss is immutbblf bnd tirfbd-sbff.
     */
    stbtid finbl dlbss LodblfStorf {
        /**
         * Mbp of vbluf to tfxt.
         */
        privbtf finbl Mbp<TfxtStylf, Mbp<Long, String>> vblufTfxtMbp;
        /**
         * Pbrsbblf dbtb.
         */
        privbtf finbl Mbp<TfxtStylf, List<Entry<String, Long>>> pbrsbblf;

        /**
         * Construdtor.
         *
         * @pbrbm vblufTfxtMbp  tif mbp of vblufs to tfxt to storf, bssignfd bnd not bltfrfd, not null
         */
        LodblfStorf(Mbp<TfxtStylf, Mbp<Long, String>> vblufTfxtMbp) {
            tiis.vblufTfxtMbp = vblufTfxtMbp;
            Mbp<TfxtStylf, List<Entry<String, Long>>> mbp = nfw HbsiMbp<>();
            List<Entry<String, Long>> bllList = nfw ArrbyList<>();
            for (Mbp.Entry<TfxtStylf, Mbp<Long, String>> vtmEntry : vblufTfxtMbp.fntrySft()) {
                Mbp<String, Entry<String, Long>> rfvfrsf = nfw HbsiMbp<>();
                for (Mbp.Entry<Long, String> fntry : vtmEntry.gftVbluf().fntrySft()) {
                    if (rfvfrsf.put(fntry.gftVbluf(), drfbtfEntry(fntry.gftVbluf(), fntry.gftKfy())) != null) {
                        // TODO: BUG: tiis ibs no ffffdt
                        dontinuf;  // not pbrsbblf, try nfxt stylf
                    }
                }
                List<Entry<String, Long>> list = nfw ArrbyList<>(rfvfrsf.vblufs());
                Collfdtions.sort(list, COMPARATOR);
                mbp.put(vtmEntry.gftKfy(), list);
                bllList.bddAll(list);
                mbp.put(null, bllList);
            }
            Collfdtions.sort(bllList, COMPARATOR);
            tiis.pbrsbblf = mbp;
        }

        /**
         * Gfts tif tfxt for tif spfdififd fifld vbluf, lodblf bnd stylf
         * for tif purposf of printing.
         *
         * @pbrbm vbluf  tif vbluf to gft tfxt for, not null
         * @pbrbm stylf  tif stylf to gft tfxt for, not null
         * @rfturn tif tfxt for tif fifld vbluf, null if no tfxt found
         */
        String gftTfxt(long vbluf, TfxtStylf stylf) {
            Mbp<Long, String> mbp = vblufTfxtMbp.gft(stylf);
            rfturn mbp != null ? mbp.gft(vbluf) : null;
        }

        /**
         * Gfts bn itfrbtor of tfxt to fifld for tif spfdififd stylf for tif purposf of pbrsing.
         * <p>
         * Tif itfrbtor must bf rfturnfd in ordfr from tif longfst tfxt to tif siortfst.
         *
         * @pbrbm stylf  tif stylf to gft tfxt for, null for bll pbrsbblf tfxt
         * @rfturn tif itfrbtor of tfxt to fifld pbirs, in ordfr from longfst tfxt to siortfst tfxt,
         *  null if tif stylf is not pbrsbblf
         */
        Itfrbtor<Entry<String, Long>> gftTfxtItfrbtor(TfxtStylf stylf) {
            List<Entry<String, Long>> list = pbrsbblf.gft(stylf);
            rfturn list != null ? list.itfrbtor() : null;
        }
    }
}
