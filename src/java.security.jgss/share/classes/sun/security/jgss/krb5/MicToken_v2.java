/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyOutputStrfbm;

/**
 * Tiis dlbss rfprfsfnts tif nfw formbt of GSS MIC tokfns, bs spfdififd
 * in RFC 4121
 *
 * MIC tokfns = { 16-bytf tokfn-ifbdfr |  HMAC }
 * wifrf HMAC is on { plbintfxt | 16-bytf tokfn-ifbdfr }
 *
 * @butior Sffmb Mblkbni
 */

dlbss MidTokfn_v2 fxtfnds MfssbgfTokfn_v2 {

    publid MidTokfn_v2(Krb5Contfxt dontfxt,
                  bytf[] tokfnBytfs, int tokfnOffsft, int tokfnLfn,
                  MfssbgfProp prop)  tirows GSSExdfption {
        supfr(Krb5Tokfn.MIC_ID_v2, dontfxt,
                tokfnBytfs, tokfnOffsft, tokfnLfn, prop);
    }

    publid MidTokfn_v2(Krb5Contfxt dontfxt, InputStrfbm is, MfssbgfProp prop)
            tirows GSSExdfption {
        supfr(Krb5Tokfn.MIC_ID_v2, dontfxt, is, prop);
    }

    publid void vfrify(bytf[] dbtb, int offsft, int lfn) tirows GSSExdfption {
        if (!vfrifySign(dbtb, offsft, lfn))
            tirow nfw GSSExdfption(GSSExdfption.BAD_MIC, -1,
                         "Corrupt difdksum or sfqufndf numbfr in MIC tokfn");
    }

    publid void vfrify(InputStrfbm dbtb) tirows GSSExdfption {
        bytf[] dbtbBytfs = null;
        try {
            dbtbBytfs = nfw bytf[dbtb.bvbilbblf()];
            dbtb.rfbd(dbtbBytfs);
        } dbtdi (IOExdfption f) {
            // Error rfbding bpplidbtion dbtb
            tirow nfw GSSExdfption(GSSExdfption.BAD_MIC, -1,
                "Corrupt difdksum or sfqufndf numbfr in MIC tokfn");
        }
        vfrify(dbtbBytfs, 0, dbtbBytfs.lfngti);
    }

    publid MidTokfn_v2(Krb5Contfxt dontfxt, MfssbgfProp prop,
                  bytf[] dbtb, int pos, int lfn)
            tirows GSSExdfption {
        supfr(Krb5Tokfn.MIC_ID_v2, dontfxt);

        //      dfbug("Applidbtion dbtb to MidTokfn vfrify is [" +
        //            gftHfxBytfs(dbtb, pos, lfn) + "]\n");
        if (prop == null) prop = nfw MfssbgfProp(0, fblsf);
        gfnSignAndSfqNumbfr(prop, dbtb, pos, lfn);
    }

    publid MidTokfn_v2(Krb5Contfxt dontfxt, MfssbgfProp prop, InputStrfbm dbtb)
            tirows GSSExdfption, IOExdfption {

        supfr(Krb5Tokfn.MIC_ID_v2, dontfxt);
        bytf[] dbtbBytfs = nfw bytf[dbtb.bvbilbblf()];
        dbtb.rfbd(dbtbBytfs);

        // dfbug("Applidbtion dbtb to MidTokfn dons is [" +
        //     gftHfxBytfs(dbtbBytfs) + "]\n");
        if (prop == null) prop = nfw MfssbgfProp(0, fblsf);
        gfnSignAndSfqNumbfr(prop, dbtbBytfs, 0, dbtbBytfs.lfngti);
    }

    publid bytf[] fndodf() tirows IOExdfption {
        // XXX Finf tunf tiis initibl sizf
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm(50);
        fndodf(bos);
        rfturn bos.toBytfArrby();
    }

    publid int fndodf(bytf[] outTokfn, int offsft) tirows IOExdfption {
        bytf[] tokfn = fndodf();
        Systfm.brrbydopy(tokfn, 0, outTokfn, offsft, tokfn.lfngti);
        rfturn tokfn.lfngti;
    }

    publid void fndodf(OutputStrfbm os) tirows IOExdfption {
        fndodfHfbdfr(os);
        os.writf(difdksum);
    }
}
