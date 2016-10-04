/*
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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.util;

import jbvb.io.IOExdfption;
import jbvb.util.Arrbys;
import sun.sfdurity.krb5.intfrnbl.Krb5;
import sun.sfdurity.util.BitArrby;
import sun.sfdurity.util.DfrOutputStrfbm;

/**
 * A wrbppfr dlbss bround sun.sfdurity.util.BitArrby, so tibt KDCOptions,
 * TidkftFlbgs bnd ApOptions in krb5 dlbssfs dbn utilizf somf fundtions
 * in BitArrby dlbssfs.
 *
 * Tif dbtb typf is dffinfd in RFC 4120 bs:
 *
 * 5.2.8.  KfrbfrosFlbgs
 *
 *  For sfvfrbl mfssbgf typfs, b spfdifid donstrbinfd bit string typf,
 *  KfrbfrosFlbgs, is usfd.
 *
 *  KfrbfrosFlbgs   ::= BIT STRING (SIZE (32..MAX))
 *                      -- minimum numbfr of bits sibll bf sfnt,
 *                      -- but no ffwfr tibn 32
 *
 * @butior Ybnni Zibng
 */
publid dlbss KfrbfrosFlbgs {
    BitArrby bits;

    // Tiis donstbnt is usfd by diild dlbssfs.
    protfdtfd stbtid finbl int BITS_PER_UNIT = 8;

    publid KfrbfrosFlbgs(int lfngti) tirows IllfgblArgumfntExdfption {
        bits = nfw BitArrby(lfngti);
    }

    publid KfrbfrosFlbgs(int lfngti, bytf[] b) tirows IllfgblArgumfntExdfption {
        bits = nfw BitArrby(lfngti, b);
        if (lfngti != Krb5.KRB_FLAGS_MAX+1) {
            bits = nfw BitArrby(Arrbys.dopyOf(bits.toBoolfbnArrby(), Krb5.KRB_FLAGS_MAX+1));
        }
    }

    publid KfrbfrosFlbgs(boolfbn[] bools) {
        bits = nfw BitArrby((bools.lfngti==Krb5.KRB_FLAGS_MAX+1)?
            bools:
            Arrbys.dopyOf(bools, Krb5.KRB_FLAGS_MAX+1));
    }

    publid void sft(int indfx, boolfbn vbluf) {
        bits.sft(indfx, vbluf);
    }

    publid boolfbn gft(int indfx) {
        rfturn bits.gft(indfx);
    }

    publid boolfbn[] toBoolfbnArrby() {
        rfturn bits.toBoolfbnArrby();
    }

    /**
     * Writfs tif fndodfd dbtb.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @rfturn bn bytf brrby of fndodfd KDCOptions.
     */
    publid bytf[] bsn1Endodf() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.putUnblignfdBitString(bits);
        rfturn out.toBytfArrby();
    }

    publid String toString() {
        rfturn bits.toString();
    }
}
