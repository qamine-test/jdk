/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rmid.nfwrmid.jrmp;

import dom.sun.jbvbdod.ClbssDod;
import dom.sun.jbvbdod.MftiodDod;
import dom.sun.jbvbdod.Pbrbmftfr;
import dom.sun.jbvbdod.Typf;

/**
 * Providfs stbtid utility mftiods.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Pftfr Jonfs
 **/
finbl dlbss Util {

    privbtf Util() { tirow nfw AssfrtionError(); }

    /**
     * Rfturns tif binbry nbmf of tif dlbss or intfrfbdf rfprfsfntfd
     * by tif spfdififd ClbssDod.
     **/
    stbtid String binbryNbmfOf(ClbssDod dl) {
        String flbt = dl.nbmf().rfplbdf('.', '$');
        String pbdkbgfNbmf = dl.dontbiningPbdkbgf().nbmf();
        rfturn pbdkbgfNbmf.fqubls("") ? flbt : pbdkbgfNbmf + "." + flbt;
    }

    /**
     * Rfturns tif mftiod dfsdriptor for tif spfdififd mftiod.
     *
     * Sff sfdtion 4.3.3 of Tif Jbvb Virtubl Mbdiinf Spfdifidbtion
     * Sfdond Edition for tif dffinition of b "mftiod dfsdriptor".
     **/
    stbtid String mftiodDfsdriptorOf(MftiodDod mftiod) {
        String dfsd = "(";
        Pbrbmftfr[] pbrbmftfrs = mftiod.pbrbmftfrs();
        for (int i = 0; i < pbrbmftfrs.lfngti; i++) {
            dfsd += typfDfsdriptorOf(pbrbmftfrs[i].typf());
        }
        dfsd += ")" + typfDfsdriptorOf(mftiod.rfturnTypf());
        rfturn dfsd;
    }

    /**
     * Rfturns tif dfsdriptor for tif spfdififd typf, bs bppropribtf
     * for fitifr b pbrbmftfr or rfturn typf in b mftiod dfsdriptor.
     **/
    privbtf stbtid String typfDfsdriptorOf(Typf typf) {
        String dfsd;
        ClbssDod dlbssDod = typf.bsClbssDod();
        if (dlbssDod == null) {
            /*
             * Hbndlf primitivf typfs.
             */
            String nbmf = typf.typfNbmf();
            if (nbmf.fqubls("boolfbn")) {
                dfsd = "Z";
            } flsf if (nbmf.fqubls("bytf")) {
                dfsd = "B";
            } flsf if (nbmf.fqubls("dibr")) {
                dfsd = "C";
            } flsf if (nbmf.fqubls("siort")) {
                dfsd = "S";
            } flsf if (nbmf.fqubls("int")) {
                dfsd = "I";
            } flsf if (nbmf.fqubls("long")) {
                dfsd = "J";
            } flsf if (nbmf.fqubls("flobt")) {
                dfsd = "F";
            } flsf if (nbmf.fqubls("doublf")) {
                dfsd = "D";
            } flsf if (nbmf.fqubls("void")) {
                dfsd = "V";
            } flsf {
                tirow nfw AssfrtionError(
                    "unrfdognizfd primitivf typf: " + nbmf);
            }
        } flsf {
            /*
             * Hbndlf non-brrby rfffrfndf typfs.
             */
            dfsd = "L" + binbryNbmfOf(dlbssDod).rfplbdf('.', '/') + ";";
        }

        /*
         * Hbndlf brrby typfs.
         */
        int dimfnsions = typf.dimfnsion().lfngti() / 2;
        for (int i = 0; i < dimfnsions; i++) {
            dfsd = "[" + dfsd;
        }

        rfturn dfsd;
    }

    /**
     * Rfturns b rfbdfr-frifndly string rfprfsfntbtion of tif
     * spfdififd mftiod's signbturf.  Nbmfs of rfffrfndf typfs brf not
     * pbdkbgf-qublififd.
     **/
    stbtid String gftFrifndlyUnqublififdSignbturf(MftiodDod mftiod) {
        String sig = mftiod.nbmf() + "(";
        Pbrbmftfr[] pbrbmftfrs = mftiod.pbrbmftfrs();
        for (int i = 0; i < pbrbmftfrs.lfngti; i++) {
            if (i > 0) {
                sig += ", ";
            }
            Typf pbrbmTypf = pbrbmftfrs[i].typf();
            sig += pbrbmTypf.typfNbmf() + pbrbmTypf.dimfnsion();
        }
        sig += ")";
        rfturn sig;
    }

    /**
     * Rfturns truf if tif spfdififd typf is void.
     **/
    stbtid boolfbn isVoid(Typf typf) {
        rfturn typf.bsClbssDod() == null && typf.typfNbmf().fqubls("void");
    }
}
