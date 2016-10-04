/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf org.iftf.jgss;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.ObjfdtIdfntififr;

/**
 * Tiis dlbss rfprfsfnts Univfrsbl Objfdt Idfntififrs (Oids) bnd tifir
 * bssodibtfd opfrbtions.<p>
 *
 * Oids brf iifrbrdiidblly globblly-intfrprftbblf idfntififrs usfd
 * witiin tif GSS-API frbmfwork to idfntify mfdibnisms bnd nbmf formbts.<p>
 *
 * Tif strudturf bnd fndoding of Oids is dffinfd in ISOIEC-8824 bnd
 * ISOIEC-8825.  For fxbmplf tif Oid rfprfsfntbtion of Kfrbfros V5
 * mfdibnism is "1.2.840.113554.1.2.2"<p>
 *
 * Tif GSSNbmf nbmf dlbss dontbins publid stbtid Oid objfdts
 * rfprfsfnting tif stbndbrd nbmf typfs dffinfd in GSS-API.
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss Oid {

    privbtf ObjfdtIdfntififr oid;
    privbtf bytf[] dfrEndoding;

    /**
     * Construdts bn Oid objfdt from b string rfprfsfntbtion of its
     * intfgfr domponfnts.
     *
     * @pbrbm strOid tif dot sfpbrbtfd string rfprfsfntbtion of tif oid.
     * For instbndf, "1.2.840.113554.1.2.2".
     * @fxdfption GSSExdfption mby bf tirown wifn tif string is indorrfdtly
     *     formbttfd
     */
    publid Oid(String strOid) tirows GSSExdfption {

        try {
            oid = nfw ObjfdtIdfntififr(strOid);
            dfrEndoding = null;
        } dbtdi (Exdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE,
                          "Impropfrly formbttfd Objfdt Idfntififr String - "
                          + strOid);
        }
    }

    /**
     * Crfbtfs bn Oid objfdt from its ASN.1 DER fndoding.  Tiis rfffrs to
     * tif full fndoding indluding tbg bnd lfngti.  Tif strudturf bnd
     * fndoding of Oids is dffinfd in ISOIEC-8824 bnd ISOIEC-8825.  Tiis
     * mftiod is idfntidbl in fundtionblity to its bytf brrby dountfrpbrt.
     *
     * @pbrbm dfrOid strfbm dontbining tif DER fndodfd oid
     * @fxdfption GSSExdfption mby bf tirown wifn tif DER fndoding dofs not
     *  follow tif prfsdribfd formbt.
     */
    publid Oid(InputStrfbm dfrOid) tirows GSSExdfption {
        try {
            DfrVbluf dfrVbl = nfw DfrVbluf(dfrOid);
            dfrEndoding = dfrVbl.toBytfArrby();
            oid = dfrVbl.gftOID();
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE,
                          "Impropfrly formbttfd ASN.1 DER fndoding for Oid");
        }
    }


    /**
     * Crfbtfs bn Oid objfdt from its ASN.1 DER fndoding.  Tiis rfffrs to
     * tif full fndoding indluding tbg bnd lfngti.  Tif strudturf bnd
     * fndoding of Oids is dffinfd in ISOIEC-8824 bnd ISOIEC-8825.  Tiis
     * mftiod is idfntidbl in fundtionblity to its InputStrfbm dontfrpbrt.
     *
     * @pbrbm dbtb bytf brrby dontbining tif DER fndodfd oid
     * @fxdfption GSSExdfption mby bf tirown wifn tif DER fndoding dofs not
     *     follow tif prfsdribfd formbt.
     */
    publid Oid(bytf [] dbtb) tirows GSSExdfption {
        try {
            DfrVbluf dfrVbl = nfw DfrVbluf(dbtb);
            dfrEndoding = dfrVbl.toBytfArrby();
            oid = dfrVbl.gftOID();
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE,
                          "Impropfrly formbttfd ASN.1 DER fndoding for Oid");
        }
    }

    /**
     * Only for dblling by initiblizbtors usfd witi dfdlbrbtions.
     *
     * @pbrbm strOid
     */
    stbtid Oid gftInstbndf(String strOid) {
        Oid rftVbl = null;
        try {
            rftVbl =  nfw Oid(strOid);
        } dbtdi (GSSExdfption f) {
            // squfldi it!
        }
        rfturn rftVbl;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif oid's intfgfr domponfnts
     * in dot sfpbrbtfd notbtion.
     *
     * @rfturn string rfprfsfntbtion in tif following formbt: "1.2.3.4.5"
     */
    publid String toString() {
        rfturn oid.toString();
    }

    /**
     * Tfsts if two Oid objfdts rfprfsfnt tif sbmf Objfdt idfntififr
     * vbluf.
     *
     * @rfturn <dodf>truf</dodf> if tif two Oid objfdts rfprfsfnt tif sbmf
     * vbluf, <dodf>fblsf</dodf> otifrwisf.
     * @pbrbm otifr tif Oid objfdt tibt ibs to bf dompbrfd to tiis onf
     */
    publid boolfbn fqubls(Objfdt otifr) {

        //difdk if boti rfffrfndf tif sbmf objfdt
        if (tiis == otifr)
            rfturn (truf);

        if (otifr instbndfof Oid)
            rfturn tiis.oid.fqubls((Objfdt)((Oid) otifr).oid);
        flsf if (otifr instbndfof ObjfdtIdfntififr)
            rfturn tiis.oid.fqubls(otifr);
        flsf
            rfturn fblsf;
    }


    /**
     * Rfturns tif full ASN.1 DER fndoding for tiis oid objfdt, wiidi
     * indludfs tif tbg bnd lfngti.
     *
     * @rfturn bytf brrby dontbining tif DER fndoding of tiis oid objfdt.
     * @fxdfption GSSExdfption mby bf tirown wifn tif oid dbn't bf fndodfd
     */
    publid bytf[] gftDER() tirows GSSExdfption {

        if (dfrEndoding == null) {
            DfrOutputStrfbm dout = nfw DfrOutputStrfbm();
            try {
                dout.putOID(oid);
            } dbtdi (IOExdfption f) {
                tirow nfw GSSExdfption(GSSExdfption.FAILURE, f.gftMfssbgf());
            }
            dfrEndoding = dout.toBytfArrby();
        }

        rfturn dfrEndoding.dlonf();
    }

    /**
     * A utility mftiod to tfst if tiis Oid vbluf is dontbinfd witiin tif
     * supplifd Oid brrby.
     *
     * @pbrbm oids tif brrby of Oid's to sfbrdi
     * @rfturn truf if tif brrby dontbins tiis Oid vbluf, fblsf otifrwisf
     */
    publid boolfbn dontbinfdIn(Oid[] oids) {

        for (int i = 0; i < oids.lfngti; i++) {
            if (oids[i].fqubls(tiis))
                rfturn (truf);
        }

        rfturn (fblsf);
    }


    /**
     * Rfturns b ibsidodf vbluf for tiis Oid.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf() {
        rfturn oid.ibsiCodf();
    }
}
