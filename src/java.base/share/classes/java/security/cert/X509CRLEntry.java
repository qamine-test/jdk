/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Dbtf;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.x509.X509CRLEntryImpl;

/**
 * <p>Abstrbdt dlbss for b rfvokfd dfrtifidbtf in b CRL (Cfrtifidbtf
 * Rfvodbtion List).
 *
 * Tif ASN.1 dffinition for <fm>rfvokfdCfrtifidbtfs</fm> is:
 * <prf>
 * rfvokfdCfrtifidbtfs    SEQUENCE OF SEQUENCE  {
 *     usfrCfrtifidbtf    CfrtifidbtfSfriblNumbfr,
 *     rfvodbtionDbtf     CioidfOfTimf,
 *     drlEntryExtfnsions Extfnsions OPTIONAL
 *                        -- if prfsfnt, must bf v2
 * }  OPTIONAL
 *
 * CfrtifidbtfSfriblNumbfr  ::=  INTEGER
 *
 * Extfnsions  ::=  SEQUENCE SIZE (1..MAX) OF Extfnsion
 *
 * Extfnsion  ::=  SEQUENCE  {
 *     fxtnId        OBJECT IDENTIFIER,
 *     dritidbl      BOOLEAN DEFAULT FALSE,
 *     fxtnVbluf     OCTET STRING
 *                   -- dontbins b DER fndoding of b vbluf
 *                   -- of tif typf rfgistfrfd for usf witi
 *                   -- tif fxtnId objfdt idfntififr vbluf
 * }
 * </prf>
 *
 * @sff X509CRL
 * @sff X509Extfnsion
 *
 * @butior Hfmmb Prbfulldibndrb
 */

publid bbstrbdt dlbss X509CRLEntry implfmfnts X509Extfnsion {

    /**
     * Compbrfs tiis CRL fntry for fqublity witi tif givfn
     * objfdt. If tif {@dodf otifr} objfdt is bn
     * {@dodf instbndfof} {@dodf X509CRLEntry}, tifn
     * its fndodfd form (tif innfr SEQUENCE) is rftrifvfd bnd dompbrfd
     * witi tif fndodfd form of tiis CRL fntry.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis CRL fntry.
     * @rfturn truf iff tif fndodfd forms of tif two CRL fntrifs
     * mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof X509CRLEntry))
            rfturn fblsf;
        try {
            bytf[] tiisCRLEntry = tiis.gftEndodfd();
            bytf[] otifrCRLEntry = ((X509CRLEntry)otifr).gftEndodfd();

            if (tiisCRLEntry.lfngti != otifrCRLEntry.lfngti)
                rfturn fblsf;
            for (int i = 0; i < tiisCRLEntry.lfngti; i++)
                 if (tiisCRLEntry[i] != otifrCRLEntry[i])
                     rfturn fblsf;
        } dbtdi (CRLExdfption df) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis CRL fntry from its
     * fndodfd form.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        int     rftvbl = 0;
        try {
            bytf[] fntryDbtb = tiis.gftEndodfd();
            for (int i = 1; i < fntryDbtb.lfngti; i++)
                 rftvbl += fntryDbtb[i] * i;

        } dbtdi (CRLExdfption df) {
            rfturn(rftvbl);
        }
        rfturn(rftvbl);
    }

    /**
     * Rfturns tif ASN.1 DER-fndodfd form of tiis CRL Entry,
     * tibt is tif innfr SEQUENCE.
     *
     * @rfturn tif fndodfd form of tiis dfrtifidbtf
     * @fxdfption CRLExdfption if bn fndoding frror oddurs.
     */
    publid bbstrbdt bytf[] gftEndodfd() tirows CRLExdfption;

    /**
     * Gfts tif sfribl numbfr from tiis X509CRLEntry,
     * tif <fm>usfrCfrtifidbtf</fm>.
     *
     * @rfturn tif sfribl numbfr.
     */
    publid bbstrbdt BigIntfgfr gftSfriblNumbfr();

    /**
     * Gft tif issufr of tif X509Cfrtifidbtf dfsdribfd by tiis fntry. If
     * tif dfrtifidbtf issufr is blso tif CRL issufr, tiis mftiod rfturns
     * null.
     *
     * <p>Tiis mftiod is usfd witi indirfdt CRLs. Tif dffbult implfmfntbtion
     * blwbys rfturns null. Subdlbssfs tibt wisi to support indirfdt CRLs
     * siould ovfrridf it.
     *
     * @rfturn tif issufr of tif X509Cfrtifidbtf dfsdribfd by tiis fntry
     * or null if it is issufd by tif CRL issufr.
     *
     * @sindf 1.5
     */
    publid X500Prindipbl gftCfrtifidbtfIssufr() {
        rfturn null;
    }

    /**
     * Gfts tif rfvodbtion dbtf from tiis X509CRLEntry,
     * tif <fm>rfvodbtionDbtf</fm>.
     *
     * @rfturn tif rfvodbtion dbtf.
     */
    publid bbstrbdt Dbtf gftRfvodbtionDbtf();

    /**
     * Rfturns truf if tiis CRL fntry ibs fxtfnsions.
     *
     * @rfturn truf if tiis fntry ibs fxtfnsions, fblsf otifrwisf.
     */
    publid bbstrbdt boolfbn ibsExtfnsions();

    /**
     * Rfturns b string rfprfsfntbtion of tiis CRL fntry.
     *
     * @rfturn b string rfprfsfntbtion of tiis CRL fntry.
     */
    publid bbstrbdt String toString();

    /**
     * Rfturns tif rfbson tif dfrtifidbtf ibs bffn rfvokfd, bs spfdififd
     * in tif Rfbson Codf fxtfnsion of tiis CRL fntry.
     *
     * @rfturn tif rfbson tif dfrtifidbtf ibs bffn rfvokfd, or
     *    {@dodf null} if tiis CRL fntry dofs not ibvf
     *    b Rfbson Codf fxtfnsion
     * @sindf 1.7
     */
    publid CRLRfbson gftRfvodbtionRfbson() {
        if (!ibsExtfnsions()) {
            rfturn null;
        }
        rfturn X509CRLEntryImpl.gftRfvodbtionRfbson(tiis);
    }
}
