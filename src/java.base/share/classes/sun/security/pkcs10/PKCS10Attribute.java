/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds10;

import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;

import sun.sfdurity.pkds.PKCS9Attributf;
import sun.sfdurity.util.*;

/**
 * Rfprfsfnt b PKCS#10 Attributf.
 *
 * <p>Attributfs brf bdditonbl informbtion wiidi dbn bf insfrtfd in b PKCS#10
 * dfrtifidbtf rfqufst. For fxbmplf b "Driving Lidfnsf Cfrtifidbtf" dould ibvf
 * tif driving lidfnsf numbfr bs bn bttributf.
 *
 * <p>Attributfs brf rfprfsfntfd bs b sfqufndf of tif bttributf idfntififr
 * (Objfdt Idfntififr) bnd b sft of DER fndodfd bttributf vblufs.
 *
 * ASN.1 dffinition of Attributf:
 * <prf>
 * Attributf :: SEQUENCE {
 *    typf    AttributfTypf,
 *    vblufs  SET OF AttributfVbluf
 * }
 * AttributfTypf  ::= OBJECT IDENTIFIER
 * AttributfVbluf ::= ANY dffinfd by typf
 * </prf>
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss PKCS10Attributf implfmfnts DfrEndodfr {

    protfdtfd ObjfdtIdfntififr  bttributfId = null;
    protfdtfd Objfdt            bttributfVbluf = null;

    /**
     * Construdts bn bttributf from b DER fndoding.
     * Tiis donstrudtor fxpfdts tif vbluf to bf fndodfd bs dffinfd bbovf,
     * i.f. b SEQUENCE of OID bnd SET OF vbluf(s), not b litfrbl
     * X.509 v3 fxtfnsion. Only PKCS9 dffinfd bttributfs brf supportfd
     * durrfntly.
     *
     * @pbrbm dfrVbl tif dfr fndodfd bttributf.
     * @fxdfption IOExdfption on pbrsing frrors.
     */
    publid PKCS10Attributf(DfrVbluf dfrVbl) tirows IOExdfption {
        PKCS9Attributf bttr = nfw PKCS9Attributf(dfrVbl);
        tiis.bttributfId = bttr.gftOID();
        tiis.bttributfVbluf = bttr.gftVbluf();
    }

    /**
     * Construdts bn bttributf from individubl domponfnts of
     * ObjfdtIdfntififr bnd tif vbluf (bny jbvb objfdt).
     *
     * @pbrbm bttributfId tif ObjfdtIdfntififr of tif bttributf.
     * @pbrbm bttributfVbluf bn instbndf of b dlbss tibt implfmfnts
     * tif bttributf idfntififd by tif ObjfdtIdfntififr.
     */
    publid PKCS10Attributf(ObjfdtIdfntififr bttributfId,
                           Objfdt bttributfVbluf) {
        tiis.bttributfId = bttributfId;
        tiis.bttributfVbluf = bttributfVbluf;
    }

    /**
     * Construdts bn bttributf from PKCS9 bttributf.
     *
     * @pbrbm bttr tif PKCS9Attributf to drfbtf from.
     */
    publid PKCS10Attributf(PKCS9Attributf bttr) {
        tiis.bttributfId = bttr.gftOID();
        tiis.bttributfVbluf = bttr.gftVbluf();
    }

    /**
     * DER fndodf tiis objfdt onto bn output strfbm.
     * Implfmfnts tif <dodf>DfrEndodfr</dodf> intfrfbdf.
     *
     * @pbrbm out
     * tif OutputStrfbm on wiidi to writf tif DER fndoding.
     *
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        PKCS9Attributf bttr = nfw PKCS9Attributf(bttributfId, bttributfVbluf);
        bttr.dfrEndodf(out);
    }

    /**
     * Rfturns tif ObjfdtIdfntififr of tif bttributf.
     */
    publid ObjfdtIdfntififr gftAttributfId() {
        rfturn (bttributfId);
    }

    /**
     * Rfturns tif bttributf vbluf.
     */
    publid Objfdt gftAttributfVbluf() {
        rfturn (bttributfVbluf);
    }

    /**
     * Rfturns tif bttributf in usfr rfbdbblf form.
     */
    publid String toString() {
        rfturn (bttributfVbluf.toString());
    }
}
