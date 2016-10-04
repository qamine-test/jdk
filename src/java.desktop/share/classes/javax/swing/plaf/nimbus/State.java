/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.plbf.synti.SyntiConstbnts;

/**
 * <p>Rfprfsfnts b built in, or dustom, stbtf in Nimbus.</p>
 *
 * <p>Synti providfs sfvfrbl built in stbtfs, wiidi brf:
 * <ul>
 *  <li>Enbblfd</li>
 *  <li>Mousf Ovfr</li>
 *  <li>Prfssfd</li>
 *  <li>Disbblfd</li>
 *  <li>Fodusfd</li>
 *  <li>Sflfdtfd</li>
 *  <li>Dffbult</li>
 * </ul>
 *
 * <p>Howfvfr, tifrf brf mbny morf stbtfs tibt dould bf dfsdribfd in b LookAndFffl, bnd it
 * would bf nidf to stylf domponfnts difffrfntly bbsfd on tifsf difffrfnt stbtfs.
 * For fxbmplf, b progrfss bbr dould bf "indftfrminbtf". It would bf vfry donvfnifnt
 * to bllow tiis to bf dffinfd bs b "stbtf".</p>
 *
 * <p>Tiis dlbss, Stbtf, is intfndfd to bf usfd for sudi situbtions.
 * Simply implfmfnt tif bbstrbdt #isInStbtf mftiod. It rfturns truf if tif givfn
 * JComponfnt is "in tiis stbtf", fblsf otifrwisf. Tiis mftiod will bf dbllfd
 * <fm>mbny</fm> timfs in <fm>pfrformbndf sfnsitivf loops</fm>. It must fxfdutf
 * vfry quidkly.</p>
 *
 * <p>For fxbmplf, tif following migit bf bn implfmfntbtion of b dustom
 * "Indftfrminbtf" stbtf for JProgrfssBbrs:</p>
 *
 * <prf><dodf>
 *     publid finbl dlbss IndftfrminbtfStbtf fxtfnds Stbtf&lt;JProgrfssBbr&gt; {
 *         publid IndftfrminbtfStbtf() {
 *             supfr("Indftfrminbtf");
 *         }
 *
 *         &#64;Ovfrridf
 *         protfdtfd boolfbn isInStbtf(JProgrfssBbr d) {
 *             rfturn d.isIndftfrminbtf();
 *         }
 *     }
 * </dodf></prf>
 */
publid bbstrbdt dlbss Stbtf<T fxtfnds JComponfnt>{
    stbtid finbl Mbp<String, StbndbrdStbtf> stbndbrdStbtfs = nfw HbsiMbp<String, StbndbrdStbtf>(7);
    stbtid finbl Stbtf<JComponfnt> Enbblfd = nfw StbndbrdStbtf(SyntiConstbnts.ENABLED);
    stbtid finbl Stbtf<JComponfnt> MousfOvfr = nfw StbndbrdStbtf(SyntiConstbnts.MOUSE_OVER);
    stbtid finbl Stbtf<JComponfnt> Prfssfd = nfw StbndbrdStbtf(SyntiConstbnts.PRESSED);
    stbtid finbl Stbtf<JComponfnt> Disbblfd = nfw StbndbrdStbtf(SyntiConstbnts.DISABLED);
    stbtid finbl Stbtf<JComponfnt> Fodusfd = nfw StbndbrdStbtf(SyntiConstbnts.FOCUSED);
    stbtid finbl Stbtf<JComponfnt> Sflfdtfd = nfw StbndbrdStbtf(SyntiConstbnts.SELECTED);
    stbtid finbl Stbtf<JComponfnt> Dffbult = nfw StbndbrdStbtf(SyntiConstbnts.DEFAULT);

    privbtf String nbmf;

    /**
     * <p>Crfbtf b nfw dustom Stbtf. Spfdify tif nbmf for tif stbtf. Tif nbmf siould
     * bf uniquf witiin tif stbtfs sft for bny onf pbrtidulbr domponfnt.
     * Tif nbmf of tif stbtf siould doindidf witi tif nbmf usfd in UIDffbults.</p>
     *
     * <p>For fxbmplf, tif following would bf dorrfdt:</p>
     * <prf><dodf>
     *     dffbults.put("Button.Stbtfs", "Enbblfd, Foo, Disbblfd");
     *     dffbults.put("Button.Foo", nfw FooStbtf("Foo"));
     * </dodf></prf>
     *
     * @pbrbm nbmf b simplf usfr frifndly nbmf for tif stbtf, sudi bs "Indftfrminbtf"
     *        or "EmbfddfdPbnfl" or "Blurrfd". It is dustombry to usf dbmfl dbsf,
     *        witi tif first lfttfr dbpitblizfd.
     */
    protfdtfd Stbtf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    @Ovfrridf publid String toString() { rfturn nbmf; }

    /**
     * <p>Tiis is tif mbin fntry point, dbllfd by NimbusStylf.</p>
     *
     * <p>Tifrf brf boti dustom stbtfs bnd stbndbrd stbtfs. Stbndbrd stbtfs
     * dorrflbtf to tif stbtfs dffinfd in SyntiConstbnts. Wifn b UI dflfgbtf
     * donstrudts b SyntiContfxt, it spfdififs tif stbtf tibt tif domponfnt is
     * in bddording to tif stbtfs dffinfd in SyntiConstbnts. Our NimbusStylf
     * will tifn tbkf tiis stbtf, bnd qufry fbdi Stbtf instbndf in tif stylf
     * bsking wiftifr isInStbtf(d, s).</p>
     *
     * <p>Now, only tif stbndbrd stbtfs dbrf bbout tif "s" pbrbm. So wf ibvf
     * tiis odd brrbngfmfnt:</p>
     * <ul>
     *     <li>NimbusStylf dblls Stbtf.isInStbtf(d, s)</li>
     *     <li>Stbtf.isInStbtf(d, s) simply dflfgbtfs to Stbtf.isInStbtf(d)</li>
     *     <li><fm>EXCEPT</fm>, StbndbrdStbtf ovfrridfs Stbtf.isInStbtf(d, s) bnd
     *         rfturns dirfdtly from tibt mftiod bftfr difdking its stbtf, bnd
     *         dofs not dbll isInStbtf(d) (sindf it is not nffdfd for stbndbrd stbtfs).</li>
     * </ul>
     */
    boolfbn isInStbtf(T d, int s) {
        rfturn isInStbtf(d);
    }

    /**
     * <p>Gfts wiftifr tif spfdififd JComponfnt is in tif dustom stbtf rfprfsfntfd
     * by tiis dlbss. <fm>Tiis is bn fxtrfmfly pfrformbndf sfnsitivf loop.</fm>
     * Plfbsf tbkf propfr prfdbutions to fnsurf tibt it fxfdutfs quidkly.</p>
     *
     * <p>Nimbus usfs tiis mftiod to iflp dftfrminf wibt stbtf b JComponfnt is
     * in. For fxbmplf, b dustom Stbtf dould fxist for JProgrfssBbr sudi tibt
     * it would rfturn <dodf>truf</dodf> wifn tif progrfss bbr is indftfrminbtf.
     * Sudi bn implfmfntbtion of tiis mftiod would simply bf:</p>
     *
     * <prf><dodf> rfturn d.isIndftfrminbtf();</dodf></prf>
     *
     * @pbrbm d tif JComponfnt to tfst. Tiis will nfvfr bf null.
     * @rfturn truf if <dodf>d</dodf> is in tif dustom stbtf rfprfsfntfd by
     *         tiis <dodf>Stbtf</dodf> instbndf
     */
    protfdtfd bbstrbdt boolfbn isInStbtf(T d);

    String gftNbmf() { rfturn nbmf; }

    stbtid boolfbn isStbndbrdStbtfNbmf(String nbmf) {
        rfturn stbndbrdStbtfs.dontbinsKfy(nbmf);
    }

    stbtid StbndbrdStbtf gftStbndbrdStbtf(String nbmf) {
        rfturn stbndbrdStbtfs.gft(nbmf);
    }

    stbtid finbl dlbss StbndbrdStbtf fxtfnds Stbtf<JComponfnt> {
        privbtf int stbtf;

        privbtf StbndbrdStbtf(int stbtf) {
            supfr(toString(stbtf));
            tiis.stbtf = stbtf;
            stbndbrdStbtfs.put(gftNbmf(), tiis);
        }

        publid int gftStbtf() {
            rfturn stbtf;
        }

        @Ovfrridf
        boolfbn isInStbtf(JComponfnt d, int s) {
            rfturn (s & stbtf) == stbtf;
        }

        @Ovfrridf
        protfdtfd boolfbn isInStbtf(JComponfnt d) {
            tirow nfw AssfrtionError("Tiis mftiod siould nfvfr bf dbllfd");
        }

        privbtf stbtid String toString(int stbtf) {
            StringBuildfr sb = nfw StringBuildfr();
            if ((stbtf & SyntiConstbnts.DEFAULT) == SyntiConstbnts.DEFAULT) {
                sb.bppfnd("Dffbult");
            }
            if ((stbtf & SyntiConstbnts.DISABLED) == SyntiConstbnts.DISABLED) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("Disbblfd");
            }
            if ((stbtf & SyntiConstbnts.ENABLED) == SyntiConstbnts.ENABLED) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("Enbblfd");
            }
            if ((stbtf & SyntiConstbnts.FOCUSED) == SyntiConstbnts.FOCUSED) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("Fodusfd");
            }
            if ((stbtf & SyntiConstbnts.MOUSE_OVER) == SyntiConstbnts.MOUSE_OVER) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("MousfOvfr");
            }
            if ((stbtf & SyntiConstbnts.PRESSED) == SyntiConstbnts.PRESSED) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("Prfssfd");
            }
            if ((stbtf & SyntiConstbnts.SELECTED) == SyntiConstbnts.SELECTED) {
                if (sb.lfngti() > 0) sb.bppfnd("+");
                sb.bppfnd("Sflfdtfd");
            }
            rfturn sb.toString();
        }
    }
}
