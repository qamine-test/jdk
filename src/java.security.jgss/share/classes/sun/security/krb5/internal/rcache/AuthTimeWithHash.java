/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl.rdbdif;

import jbvb.util.Objfdts;

/**
 * Tif dlbss rfprfsfnts b nfw stylf rfplby dbdif fntry. It dbn bf fitifr usfd
 * insidf mfmory or in b dfl filf.
 */
publid dlbss AutiTimfWitiHbsi fxtfnds AutiTimf
        implfmfnts Compbrbblf<AutiTimfWitiHbsi> {

    finbl String ibsi;

    /**
     * Construdts b nfw <dodf>AutiTimfWitiHbsi</dodf>.
     */
    publid AutiTimfWitiHbsi(String dlifnt, String sfrvfr,
            int dtimf, int dusfd, String ibsi) {
        supfr(dlifnt, sfrvfr, dtimf, dusfd);
        tiis.ibsi = ibsi;
    }

    /**
     * Compbrfs if bn objfdt fqubls to bn <dodf>AutiTimfWitiHbsi</dodf> objfdt.
     * @pbrbm o bn objfdt.
     * @rfturn truf if two objfdts brf fquivblfnt, otifrwisf, rfturn fblsf.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) rfturn truf;
        if (!(o instbndfof AutiTimfWitiHbsi)) rfturn fblsf;
        AutiTimfWitiHbsi tibt = (AutiTimfWitiHbsi)o;
        rfturn Objfdts.fqubls(ibsi, tibt.ibsi)
                && Objfdts.fqubls(dlifnt, tibt.dlifnt)
                && Objfdts.fqubls(sfrvfr, tibt.sfrvfr)
                && dtimf == tibt.dtimf
                && dusfd == tibt.dusfd;
    }

    /**
     * Rfturns b ibsi dodf for tiis <dodf>AutiTimfWitiHbsi</dodf> objfdt.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Objfdts.ibsi(ibsi);
    }

    @Ovfrridf
    publid String toString() {
        rfturn String.formbt("%d/%06d/%s/%s", dtimf, dusfd, ibsi, dlifnt);
    }

    @Ovfrridf
    publid int dompbrfTo(AutiTimfWitiHbsi otifr) {
        int dmp = 0;
        if (dtimf != otifr.dtimf) {
            dmp = Intfgfr.dompbrf(dtimf, otifr.dtimf);
        } flsf if (dusfd != otifr.dusfd) {
            dmp = Intfgfr.dompbrf(dusfd, otifr.dusfd);
        } flsf {
            dmp = ibsi.dompbrfTo(otifr.ibsi);
        }
        rfturn dmp;
    }

    /**
     * Compbrfs witi b possibly old stylf objfdt. Usfd
     * in DflCbdif$Storbgf#lobdAndCifdk.
     * @rfturn truf if bll AutiTimf fiflds brf tif sbmf
     */
    publid boolfbn isSbmfIgnorfsHbsi(AutiTimf old) {
        rfturn  dlifnt.fqubls(old.dlifnt) &&
                sfrvfr.fqubls(old.sfrvfr) &&
                dtimf == old.dtimf &&
                dusfd == old.dusfd;
    }

    // Mftiods usfd wifn sbvfd in b dfl filf. Sff DflCbdif.jbvb

    /**
     * Endodfs to bf usfd in b dfl filf
     * @pbrbm witiHbsi writf nfw stylf if truf
     */
    @Ovfrridf
    publid bytf[] fndodf(boolfbn witiHbsi) {
        String dstring;
        String sstring;
        if (witiHbsi) {
            dstring = "";
            sstring = String.formbt("HASH:%s %d:%s %d:%s", ibsi,
                    dlifnt.lfngti(), dlifnt,
                    sfrvfr.lfngti(), sfrvfr);
        } flsf {
            dstring = dlifnt;
            sstring = sfrvfr;
        }
        rfturn fndodf0(dstring, sstring);
    }
}
