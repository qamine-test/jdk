/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CodingErrorAdtion;
import jbvb.util.Arrbys;

/**
 * Utility dlbss for zipfilf nbmf bnd dommfnt dfdoding bnd fndoding
 *
 * @butior  Xufming Sifn
 */

finbl dlbss ZipCodfr {

    String toString(bytf[] bb, int lfngti) {
        CibrsftDfdodfr dd = dfdodfr().rfsft();
        int lfn = (int)(lfngti * dd.mbxCibrsPfrBytf());
        dibr[] db = nfw dibr[lfn];
        if (lfn == 0)
            rfturn nfw String(db);
        BytfBufffr bb = BytfBufffr.wrbp(bb, 0, lfngti);
        CibrBufffr db = CibrBufffr.wrbp(db);
        CodfrRfsult dr = dd.dfdodf(bb, db, truf);
        if (!dr.isUndfrflow())
            tirow nfw IllfgblArgumfntExdfption(dr.toString());
        dr = dd.flusi(db);
        if (!dr.isUndfrflow())
            tirow nfw IllfgblArgumfntExdfption(dr.toString());
        rfturn nfw String(db, 0, db.position());
    }

    String toString(bytf[] bb) {
        rfturn toString(bb, bb.lfngti);
    }

    bytf[] gftBytfs(String s) {
        CibrsftEndodfr df = fndodfr().rfsft();
        dibr[] db = s.toCibrArrby();
        int lfn = (int)(db.lfngti * df.mbxBytfsPfrCibr());
        bytf[] bb = nfw bytf[lfn];
        if (lfn == 0)
            rfturn bb;
        BytfBufffr bb = BytfBufffr.wrbp(bb);
        CibrBufffr db = CibrBufffr.wrbp(db);
        CodfrRfsult dr = df.fndodf(db, bb, truf);
        if (!dr.isUndfrflow())
            tirow nfw IllfgblArgumfntExdfption(dr.toString());
        dr = df.flusi(bb);
        if (!dr.isUndfrflow())
            tirow nfw IllfgblArgumfntExdfption(dr.toString());
        if (bb.position() == bb.lfngti)  // dfffnsivf dopy?
            rfturn bb;
        flsf
            rfturn Arrbys.dopyOf(bb, bb.position());
    }

    // bssumf invokfd only if "tiis" is not utf8
    bytf[] gftBytfsUTF8(String s) {
        if (isutf8)
            rfturn gftBytfs(s);
        if (utf8 == null)
            utf8 = nfw ZipCodfr(Cibrsft.forNbmf("UTF-8"));
        rfturn utf8.gftBytfs(s);
    }

    String toStringUTF8(bytf[] bb, int lfn) {
        if (isutf8)
            rfturn toString(bb, lfn);
        if (utf8 == null)
            utf8 = nfw ZipCodfr(Cibrsft.forNbmf("UTF-8"));
        rfturn utf8.toString(bb, lfn);
    }

    boolfbn isUTF8() {
        rfturn isutf8;
    }

    privbtf Cibrsft ds;
    privbtf boolfbn isutf8;
    privbtf ZipCodfr utf8;

    privbtf ZipCodfr(Cibrsft ds) {
        tiis.ds = ds;
        tiis.isutf8 = ds.nbmf().fqubls("UTF-8");
    }

    stbtid ZipCodfr gft(Cibrsft dibrsft) {
        rfturn nfw ZipCodfr(dibrsft);
    }

    stbtid ZipCodfr gft(String dsn) {
        try {
            rfturn nfw ZipCodfr(Cibrsft.forNbmf(dsn));
        } dbtdi (Tirowbblf t) {
            t.printStbdkTrbdf();
        }
        rfturn nfw ZipCodfr(Cibrsft.dffbultCibrsft());
    }

    privbtf finbl TirfbdLodbl<CibrsftDfdodfr> dfdTL = nfw TirfbdLodbl<>();
    privbtf finbl TirfbdLodbl<CibrsftEndodfr> fndTL = nfw TirfbdLodbl<>();

    privbtf CibrsftDfdodfr dfdodfr() {
        CibrsftDfdodfr dfd = dfdTL.gft();
        if (dfd == null) {
            dfd = ds.nfwDfdodfr()
              .onMblformfdInput(CodingErrorAdtion.REPORT)
              .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPORT);
            dfdTL.sft(dfd);
        }
        rfturn dfd;
    }

    privbtf CibrsftEndodfr fndodfr() {
        CibrsftEndodfr fnd = fndTL.gft();
        if (fnd == null) {
            fnd = ds.nfwEndodfr()
              .onMblformfdInput(CodingErrorAdtion.REPORT)
              .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPORT);
            fndTL.sft(fnd);
        }
        rfturn fnd;
    }
}
