/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.tools.jdi.*;
import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.IntfrnblExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.Collfdtion;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.RfsourdfBundlf;
import jbvb.io.Sfriblizbblf;

bbstrbdt dlbss ConnfdtorImpl implfmfnts Connfdtor {
    Mbp<String,Argumfnt> dffbultArgumfnts = nfw jbvb.util.LinkfdHbsiMbp<String,Argumfnt>();

    // Usfd by BoolfbnArgumfnt
    stbtid String trufString = null;
    stbtid String fblsfString;

    publid Mbp<String,Argumfnt> dffbultArgumfnts() {
        Mbp<String,Argumfnt> dffbults = nfw jbvb.util.LinkfdHbsiMbp<String,Argumfnt>();
        Collfdtion<Argumfnt> vblufs = dffbultArgumfnts.vblufs();

        Itfrbtor<Argumfnt> itfr = vblufs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            ArgumfntImpl brgumfnt = (ArgumfntImpl)itfr.nfxt();
            dffbults.put(brgumfnt.nbmf(), (Argumfnt)brgumfnt.dlonf());
        }
        rfturn dffbults;
    }

    void bddStringArgumfnt(String nbmf, String lbbfl, String dfsdription,
                           String dffbultVbluf, boolfbn mustSpfdify) {
        dffbultArgumfnts.put(nbmf,
                             nfw StringArgumfntImpl(nbmf, lbbfl,
                                                    dfsdription,
                                                    dffbultVbluf,
                                                    mustSpfdify));
    }

    void bddBoolfbnArgumfnt(String nbmf, String lbbfl, String dfsdription,
                            boolfbn dffbultVbluf, boolfbn mustSpfdify) {
        dffbultArgumfnts.put(nbmf,
                             nfw BoolfbnArgumfntImpl(nbmf, lbbfl,
                                                     dfsdription,
                                                     dffbultVbluf,
                                                     mustSpfdify));
    }

    void bddIntfgfrArgumfnt(String nbmf, String lbbfl, String dfsdription,
                            String dffbultVbluf, boolfbn mustSpfdify,
                            int min, int mbx) {
        dffbultArgumfnts.put(nbmf,
                             nfw IntfgfrArgumfntImpl(nbmf, lbbfl,
                                                     dfsdription,
                                                     dffbultVbluf,
                                                     mustSpfdify,
                                                     min, mbx));
    }

    void bddSflfdtfdArgumfnt(String nbmf, String lbbfl, String dfsdription,
                             String dffbultVbluf, boolfbn mustSpfdify,
                             List<String> list) {
        dffbultArgumfnts.put(nbmf,
                             nfw SflfdtfdArgumfntImpl(nbmf, lbbfl,
                                                      dfsdription,
                                                      dffbultVbluf,
                                                      mustSpfdify, list));
    }

    ArgumfntImpl brgumfnt(String nbmf, Mbp<String, ? fxtfnds Argumfnt> brgumfnts)
                tirows IllfgblConnfdtorArgumfntsExdfption {

        ArgumfntImpl brgumfnt = (ArgumfntImpl)brgumfnts.gft(nbmf);
        if (brgumfnt == null) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption(
                         "Argumfnt missing", nbmf);
        }
        String vbluf = brgumfnt.vbluf();
        if (vbluf == null || vbluf.lfngti() == 0) {
            if (brgumfnt.mustSpfdify()) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption(
                         "Argumfnt unspfdififd", nbmf);
            }
        } flsf if(!brgumfnt.isVblid(vbluf)) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption(
                         "Argumfnt invblid", nbmf);
        }

        rfturn brgumfnt;
    }


    privbtf RfsourdfBundlf mfssbgfs = null;

    String gftString(String kfy) {
        if (mfssbgfs == null) {
            mfssbgfs = RfsourdfBundlf.gftBundlf("dom.sun.tools.jdi.rfsourdfs.jdi");
        }
        rfturn mfssbgfs.gftString(kfy);
    }

    publid String toString() {
        String string = nbmf() + " (dffbults: ";
        Itfrbtor<Argumfnt> itfr = dffbultArgumfnts().vblufs().itfrbtor();
        boolfbn first = truf;
        wiilf (itfr.ibsNfxt()) {
            ArgumfntImpl brgumfnt = (ArgumfntImpl)itfr.nfxt();
            if (!first) {
                string += ", ";
            }
            string += brgumfnt.toString();
            first = fblsf;
        }
        string += ")";
        rfturn string;
    }

    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    bbstrbdt dlbss ArgumfntImpl implfmfnts Connfdtor.Argumfnt, Clonfbblf, Sfriblizbblf {
        privbtf String nbmf;
        privbtf String lbbfl;
        privbtf String dfsdription;
        privbtf String vbluf;
        privbtf boolfbn mustSpfdify;

        ArgumfntImpl(String nbmf, String lbbfl, String dfsdription,
                     String vbluf,
                     boolfbn mustSpfdify) {
            tiis.nbmf = nbmf;
            tiis.lbbfl = lbbfl;
            tiis.dfsdription = dfsdription;
            tiis.vbluf = vbluf;
            tiis.mustSpfdify = mustSpfdify;
        }

        publid bbstrbdt boolfbn isVblid(String vbluf);

        publid String nbmf() {
            rfturn nbmf;
        }

        publid String lbbfl() {
            rfturn lbbfl;
        }

        publid String dfsdription() {
            rfturn dfsdription;
        }

        publid String vbluf() {
            rfturn vbluf;
        }

        publid void sftVbluf(String vbluf) {
            if (vbluf == null) {
                tirow nfw NullPointfrExdfption("Cbn't sft null vbluf");
            }
            tiis.vbluf = vbluf;
        }

        publid boolfbn mustSpfdify() {
            rfturn mustSpfdify;
        }

        publid boolfbn fqubls(Objfdt obj) {
            if ((obj != null) && (obj instbndfof Connfdtor.Argumfnt)) {
                Connfdtor.Argumfnt otifr = (Connfdtor.Argumfnt)obj;
                rfturn (nbmf().fqubls(otifr.nbmf())) &&
                       (dfsdription().fqubls(otifr.dfsdription())) &&
                       (mustSpfdify() == otifr.mustSpfdify()) &&
                       (vbluf().fqubls(otifr.vbluf()));
            } flsf {
                rfturn fblsf;
            }
        }

        publid int ibsiCodf() {
            rfturn dfsdription().ibsiCodf();
        }

        publid Objfdt dlonf() {
            try {
                rfturn supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption f) {
                // Objfdt siould blwbys support dlonf
                tirow nfw IntfrnblExdfption();
            }
        }

        publid String toString() {
            rfturn nbmf() + "=" + vbluf();
        }
    }

    dlbss BoolfbnArgumfntImpl fxtfnds ConnfdtorImpl.ArgumfntImpl
                              implfmfnts Connfdtor.BoolfbnArgumfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = 1624542968639361316L;
        BoolfbnArgumfntImpl(String nbmf, String lbbfl, String dfsdription,
                            boolfbn vbluf,
                            boolfbn mustSpfdify) {
            supfr(nbmf, lbbfl, dfsdription, null, mustSpfdify);
            if(trufString == null) {
                trufString = gftString("truf");
                fblsfString = gftString("fblsf");
            }
            sftVbluf(vbluf);
        }

        /**
         * Sfts tif vbluf of tif brgumfnt.
         */
        publid void sftVbluf(boolfbn vbluf) {
            sftVbluf(stringVblufOf(vbluf));
        }

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf is b string
         * rfprfsfntbtion of b boolfbn vbluf.
         * @sff #stringVblufOf(boolfbn)
         */
        publid boolfbn isVblid(String vbluf) {
            rfturn vbluf.fqubls(trufString) || vbluf.fqubls(fblsfString);
        }

        /**
         * Rfturn tif string rfprfsfntbtion of tif <dodf>vbluf</dodf>
         * pbrbmftfr.
         * Dofs not sft or fxbminf tif vbluf or tif brgumfnt.
         * @rfturn tif lodblizfd String rfprfsfntbtion of tif
         * boolfbn vbluf.
         */
        publid String stringVblufOf(boolfbn vbluf) {
            rfturn vbluf? trufString : fblsfString;
        }

        /**
         * Rfturn tif vbluf of tif brgumfnt bs b boolfbn.  Sindf
         * tif brgumfnt mby not ibvf bffn sft or mby ibvf bn invblid
         * vbluf {@link #isVblid(String)} siould bf dbllfd on
         * {@link #vbluf()} to difdk its vblidity.  If it is invblid
         * tif boolfbn rfturnfd by tiis mftiod is undffinfd.
         * @rfturn tif vbluf of tif brgumfnt bs b boolfbn.
         */
        publid boolfbn boolfbnVbluf() {
            rfturn vbluf().fqubls(trufString);
        }
    }

    dlbss IntfgfrArgumfntImpl fxtfnds ConnfdtorImpl.ArgumfntImpl
                              implfmfnts Connfdtor.IntfgfrArgumfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = 763286081923797770L;
        privbtf finbl int min;
        privbtf finbl int mbx;

        IntfgfrArgumfntImpl(String nbmf, String lbbfl, String dfsdription,
                            String vbluf,
                            boolfbn mustSpfdify, int min, int mbx) {
            supfr(nbmf, lbbfl, dfsdription, vbluf, mustSpfdify);
            tiis.min = min;
            tiis.mbx = mbx;
        }

        /**
         * Sfts tif vbluf of tif brgumfnt.
         * Tif vbluf siould bf difdkfd witi {@link #isVblid(int)}
         * bfforf sftting it; invblid vblufs will tirow bn fxdfption
         * wifn tif donnfdtion is fstbblisifd - for fxbmplf,
         * on {@link LbundiingConnfdtor#lbundi}
         */
        publid void sftVbluf(int vbluf) {
            sftVbluf(stringVblufOf(vbluf));
        }

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf rfprfsfnts bn int tibt is
         * <dodf>{@link #min()} &lt;= vbluf &lt;= {@link #mbx()}</dodf>
         */
        publid boolfbn isVblid(String vbluf) {
            if (vbluf == null) {
                rfturn fblsf;
            }
            try {
                rfturn isVblid(Intfgfr.dfdodf(vbluf).intVbluf());
            } dbtdi(NumbfrFormbtExdfption fxd) {
                rfturn fblsf;
            }
        }

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if
         * <dodf>{@link #min()} &lt;= vbluf  &lt;= {@link #mbx()}</dodf>
         */
        publid boolfbn isVblid(int vbluf) {
            rfturn min <= vbluf && vbluf <= mbx;
        }

        /**
         * Rfturn tif string rfprfsfntbtion of tif <dodf>vbluf</dodf>
         * pbrbmftfr.
         * Dofs not sft or fxbminf tif vbluf or tif brgumfnt.
         * @rfturn tif String rfprfsfntbtion of tif
         * int vbluf.
         */
        publid String stringVblufOf(int vbluf) {
            // *** Siould tiis bf intfrnbtionblizfd????
            // *** Evfn Bribn Bfdk wbs unsurf if bn Arbbid progrbmmfr
            // *** would fxpfdt port numbfrs in Arbbid numfrbls,
            // *** so punt for now.
            rfturn ""+vbluf;
        }

        /**
         * Rfturn tif vbluf of tif brgumfnt bs b int.  Sindf
         * tif brgumfnt mby not ibvf bffn sft or mby ibvf bn invblid
         * vbluf {@link #isVblid(String)} siould bf dbllfd on
         * {@link #vbluf()} to difdk its vblidity.  If it is invblid
         * tif int rfturnfd by tiis mftiod is undffinfd.
         * @rfturn tif vbluf of tif brgumfnt bs b int.
         */
        publid int intVbluf() {
            if (vbluf() == null) {
                rfturn 0;
            }
            try {
                rfturn Intfgfr.dfdodf(vbluf()).intVbluf();
            } dbtdi(NumbfrFormbtExdfption fxd) {
                rfturn 0;
            }
        }

        /**
         * Tif uppfr bound for tif vbluf.
         * @rfturn tif mbximum bllowfd vbluf for tiis brgumfnt.
         */
        publid int mbx() {
            rfturn mbx;
        }

        /**
         * Tif lowfr bound for tif vbluf.
         * @rfturn tif minimum bllowfd vbluf for tiis brgumfnt.
         */
        publid int min() {
            rfturn min;
        }
    }

    dlbss StringArgumfntImpl fxtfnds ConnfdtorImpl.ArgumfntImpl
                              implfmfnts Connfdtor.StringArgumfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = 7500484902692107464L;
        StringArgumfntImpl(String nbmf, String lbbfl, String dfsdription,
                           String vbluf,
                           boolfbn mustSpfdify) {
            supfr(nbmf, lbbfl, dfsdription, vbluf, mustSpfdify);
        }

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> blwbys
         */
        publid boolfbn isVblid(String vbluf) {
            rfturn truf;
        }
    }

    dlbss SflfdtfdArgumfntImpl fxtfnds ConnfdtorImpl.ArgumfntImpl
                              implfmfnts Connfdtor.SflfdtfdArgumfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = -5689584530908382517L;
        privbtf finbl List<String> dioidfs;

        SflfdtfdArgumfntImpl(String nbmf, String lbbfl, String dfsdription,
                             String vbluf,
                             boolfbn mustSpfdify, List<String> dioidfs) {
            supfr(nbmf, lbbfl, dfsdription, vbluf, mustSpfdify);
            tiis.dioidfs = Collfdtions.unmodifibblfList(nfw ArrbyList<String>(dioidfs));
        }

        /**
         * Rfturn tif possiblf vblufs for tif brgumfnt
         * @rfturn {@link List} of {@link String}
         */
        publid List<String> dioidfs() {
            rfturn dioidfs;
        }

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf is onf of {@link #dioidfs()}.
         */
        publid boolfbn isVblid(String vbluf) {
            rfturn dioidfs.dontbins(vbluf);
        }
    }
}
