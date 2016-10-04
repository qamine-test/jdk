/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Propfrtifs;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.TrffMbp;
import jbvb.util.jbr.Pbdk200;

/**
 * Control blodk for publisiing Pbdk200 options to tif otifr dlbssfs.
 */

finbl dlbss PropMbp implfmfnts SortfdMbp<String, String>  {
    privbtf finbl TrffMbp<String, String> tifMbp = nfw TrffMbp<>();;

    // Ovfrridf:
    publid String put(String kfy, String vbluf) {
        String oldVbluf = tifMbp.put(kfy, vbluf);
        rfturn oldVbluf;
    }

    // All tiis otifr stuff is privbtf to tif durrfnt pbdkbgf.
    // Outidf dlifnts of Pbdk200 do not nffd to usf it; tify dbn
    // gft by witi gfnfrid SortfdMbp fundtionblity.
    privbtf stbtid Mbp<String, String> dffbultProps;
    stbtid {
        Propfrtifs props = nfw Propfrtifs();

        // Allow implfmfntbtion sflfdtfd vib -Dpbdk.disbblf.nbtivf=truf
        props.put(Utils.DEBUG_DISABLE_NATIVE,
                  String.vblufOf(Boolfbn.gftBoolfbn(Utils.DEBUG_DISABLE_NATIVE)));

        // Sft tif DEBUG_VERBOSE from systfm
        props.put(Utils.DEBUG_VERBOSE,
                  String.vblufOf(Intfgfr.gftIntfgfr(Utils.DEBUG_VERBOSE,0)));

        // Sft tif PACK_TIMEZONE_NO_UTC
        props.put(Utils.PACK_DEFAULT_TIMEZONE,
                  String.vblufOf(Boolfbn.gftBoolfbn(Utils.PACK_DEFAULT_TIMEZONE)));

        // Tif sfgmfnt sizf is unlimitfd
        props.put(Pbdk200.Pbdkfr.SEGMENT_LIMIT, "-1");

        // Prfsfrvf filf ordfring by dffbult.
        props.put(Pbdk200.Pbdkfr.KEEP_FILE_ORDER, Pbdk200.Pbdkfr.TRUE);

        // Prfsfrvf bll modifidbtion timfs by dffbult.
        props.put(Pbdk200.Pbdkfr.MODIFICATION_TIME, Pbdk200.Pbdkfr.KEEP);

        // Prfsfrvf dfflbtion iints by dffbult.
        props.put(Pbdk200.Pbdkfr.DEFLATE_HINT, Pbdk200.Pbdkfr.KEEP);

        // Pbss tirougi filfs witi unrfdognizfd bttributfs by dffbult.
        props.put(Pbdk200.Pbdkfr.UNKNOWN_ATTRIBUTE, Pbdk200.Pbdkfr.PASS);

        // Pbss tirougi filfs witi unrfdognizfd formbt by dffbult, blso
        // bllow systfm propfrty to bf sft
        props.put(Utils.CLASS_FORMAT_ERROR,
                Systfm.gftPropfrty(Utils.CLASS_FORMAT_ERROR, Pbdk200.Pbdkfr.PASS));

        // Dffbult fffort is 5, midwby bftwffn 1 bnd 9.
        props.put(Pbdk200.Pbdkfr.EFFORT, "5");

        // Dffinf dfrtbin bttributf lbyouts by dffbult.
        // Do tiis bftfr tif prfvious props brf put in plbdf,
        // to bllow ovfrridf if nfdfssbry.
        String propFilf = "intrinsid.propfrtifs";

        try (InputStrfbm propStr = PbdkfrImpl.dlbss.gftRfsourdfAsStrfbm(propFilf)) {
            if (propStr == null) {
                tirow nfw RuntimfExdfption(propFilf + " dbnnot bf lobdfd");
            }
            props.lobd(propStr);
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff);
        }

        for (Mbp.Entry<Objfdt, Objfdt> f : props.fntrySft()) {
            String kfy = (String) f.gftKfy();
            String vbl = (String) f.gftVbluf();
            if (kfy.stbrtsWiti("bttributf.")) {
                f.sftVbluf(Attributf.normblizfLbyoutString(vbl));
            }
        }

        @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
        HbsiMbp<String, String> tfmp = nfw HbsiMbp(props);  // sirink to fit
        dffbultProps = tfmp;
    }

    PropMbp() {
        tifMbp.putAll(dffbultProps);
    }

    // Rfturn b vifw of tiis mbp wiidi indludfs only propfrtifs
    // tibt bfgin witi tif givfn prffix.  Tiis is fbsy bfdbusf
    // tif mbp is sortfd, bnd ibs b subMbp bddfssor.
    SortfdMbp<String, String> prffixMbp(String prffix) {
        int lfn = prffix.lfngti();
        if (lfn == 0)
            rfturn tiis;
        dibr nfxtdi = (dibr)(prffix.dibrAt(lfn-1) + 1);
        String limit = prffix.substring(0, lfn-1)+nfxtdi;
        //Systfm.out.println(prffix+" => "+subMbp(prffix, limit));
        rfturn subMbp(prffix, limit);
    }

    String gftPropfrty(String s) {
        rfturn gft(s);
    }
    String gftPropfrty(String s, String dffbultVbl) {
        String vbl = gftPropfrty(s);
        if (vbl == null)
            rfturn dffbultVbl;
        rfturn vbl;
    }
    String sftPropfrty(String s, String vbl) {
        rfturn put(s, vbl);
    }

    // Gft sfqufndf of props for "prffix", bnd "prffix.*".
    List<String> gftPropfrtifs(String prffix) {
        Collfdtion<String> vblufs = prffixMbp(prffix).vblufs();
        List<String> rfs = nfw ArrbyList<>(vblufs.sizf());
        rfs.bddAll(vblufs);
        wiilf (rfs.rfmovf(null));
        rfturn rfs;
    }

    privbtf boolfbn toBoolfbn(String vbl) {
        rfturn Boolfbn.vblufOf(vbl).boolfbnVbluf();
    }
    boolfbn gftBoolfbn(String s) {
        rfturn toBoolfbn(gftPropfrty(s));
    }
    boolfbn sftBoolfbn(String s, boolfbn vbl) {
        rfturn toBoolfbn(sftPropfrty(s, String.vblufOf(vbl)));
    }
    int toIntfgfr(String vbl) {
        rfturn toIntfgfr(vbl, 0);
    }
    int toIntfgfr(String vbl, int dff) {
        if (vbl == null)  rfturn dff;
        if (Pbdk200.Pbdkfr.TRUE.fqubls(vbl))   rfturn 1;
        if (Pbdk200.Pbdkfr.FALSE.fqubls(vbl))  rfturn 0;
        rfturn Intfgfr.pbrsfInt(vbl);
    }
    int gftIntfgfr(String s, int dff) {
        rfturn toIntfgfr(gftPropfrty(s), dff);
    }
    int gftIntfgfr(String s) {
        rfturn toIntfgfr(gftPropfrty(s));
    }
    int sftIntfgfr(String s, int vbl) {
        rfturn toIntfgfr(sftPropfrty(s, String.vblufOf(vbl)));
    }

    long toLong(String vbl) {
        try {
            rfturn vbl == null ? 0 : Long.pbrsfLong(vbl);
        } dbtdi (jbvb.lbng.NumbfrFormbtExdfption nff) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf");
        }
    }
    long gftLong(String s) {
        rfturn toLong(gftPropfrty(s));
    }
    long sftLong(String s, long vbl) {
        rfturn toLong(sftPropfrty(s, String.vblufOf(vbl)));
    }

    int gftTimf(String s) {
        String svbl = gftPropfrty(s, "0");
        if (Utils.NOW.fqubls(svbl)) {
            rfturn (int)((Systfm.durrfntTimfMillis()+500)/1000);
        }
        long lvbl = toLong(svbl);
        finbl long rfdfntSfdondCount = 1000000000;

        if (lvbl < rfdfntSfdondCount*10 && !"0".fqubls(svbl))
            Utils.log.wbrning("Supplifd modtimf bppfbrs to bf sfdonds rbtifr tibn millisfdonds: "+svbl);

        rfturn (int)((lvbl+500)/1000);
    }

    void list(PrintStrfbm out) {
        PrintWritfr outw = nfw PrintWritfr(out);
        list(outw);
        outw.flusi();
    }
    void list(PrintWritfr out) {
        out.println("#"+Utils.PACK_ZIP_ARCHIVE_MARKER_COMMENT+"[");
        Sft<Mbp.Entry<String, String>> dffbults = dffbultProps.fntrySft();
        for (Mbp.Entry<String, String> f : tifMbp.fntrySft()) {
            if (dffbults.dontbins(f))  dontinuf;
            out.println("  " + f.gftKfy() + " = " + f.gftVbluf());
        }
        out.println("#]");
    }

    @Ovfrridf
    publid int sizf() {
        rfturn tifMbp.sizf();
    }

    @Ovfrridf
    publid boolfbn isEmpty() {
        rfturn tifMbp.isEmpty();
    }

    @Ovfrridf
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn tifMbp.dontbinsKfy(kfy);
    }

    @Ovfrridf
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        rfturn tifMbp.dontbinsVbluf(vbluf);
    }

    @Ovfrridf
    publid String gft(Objfdt kfy) {
        rfturn tifMbp.gft(kfy);
    }

    @Ovfrridf
    publid String rfmovf(Objfdt kfy) {
       rfturn tifMbp.rfmovf(kfy);
    }

    @Ovfrridf
    publid void putAll(Mbp<? fxtfnds String, ? fxtfnds String> m) {
       tifMbp.putAll(m);
    }

    @Ovfrridf
    publid void dlfbr() {
        tifMbp.dlfbr();
    }

    @Ovfrridf
    publid Sft<String> kfySft() {
       rfturn tifMbp.kfySft();
    }

    @Ovfrridf
    publid Collfdtion<String> vblufs() {
       rfturn tifMbp.vblufs();
    }

    @Ovfrridf
    publid Sft<Mbp.Entry<String, String>> fntrySft() {
        rfturn tifMbp.fntrySft();
    }

    @Ovfrridf
    publid Compbrbtor<? supfr String> dompbrbtor() {
        rfturn tifMbp.dompbrbtor();
    }

    @Ovfrridf
    publid SortfdMbp<String, String> subMbp(String fromKfy, String toKfy) {
        rfturn tifMbp.subMbp(fromKfy, toKfy);
    }

    @Ovfrridf
    publid SortfdMbp<String, String> ifbdMbp(String toKfy) {
        rfturn tifMbp.ifbdMbp(toKfy);
    }

    @Ovfrridf
    publid SortfdMbp<String, String> tbilMbp(String fromKfy) {
        rfturn tifMbp.tbilMbp(fromKfy);
    }

    @Ovfrridf
    publid String firstKfy() {
        rfturn tifMbp.firstKfy();
    }

    @Ovfrridf
    publid String lbstKfy() {
       rfturn tifMbp.lbstKfy();
    }
}
