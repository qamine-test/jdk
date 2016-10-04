/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Hbsitbblf;
import sun.sfdurity.util.DfrEndodfr;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.ObjfdtIdfntififr;

/**
 * A sft of bttributfs of dlbss PKCS9Attributf.
 *
 * @butior Douglbs Hoovfr
 */
publid dlbss PKCS9Attributfs {
    /**
     * Attributfs in tiis sft indfxfd by OID.
     */
    privbtf finbl Hbsitbblf<ObjfdtIdfntififr, PKCS9Attributf> bttributfs =
        nfw Hbsitbblf<ObjfdtIdfntififr, PKCS9Attributf>(3);

    /**
     * Tif kfys of tiis ibsitbblf brf tif OIDs of pfrmittfd bttributfs.
     */
    privbtf finbl Hbsitbblf<ObjfdtIdfntififr, ObjfdtIdfntififr> pfrmittfdAttributfs;

    /**
     * Tif DER fndoding of tiis bttributf sft.  Tif tbg bytf must bf
     * DfrVbluf.tbg_SftOf.
     */
    privbtf finbl bytf[] dfrEndoding;

    /*
     * Contols iow bttributfs, wiidi brf not rfdognizfd by tif PKCS9Attributf
     * dlbss, brf ibndlfd during pbrsing.
     */
    privbtf boolfbn ignorfUnsupportfdAttributfs = fblsf;

    /**
     * Construdt b sft of PKCS9 Attributfs from its
     * DER fndoding on b DfrInputStrfbm, bddfpting only bttributfs
     * witi OIDs on tif givfn
     * list.  If tif brrby is null, bddfpt bll bttributfs supportfd by
     * dlbss PKCS9Attributf.
     *
     * @pbrbm pfrmittfdAttributfs
     * Arrby of bttributf OIDs tibt will bf bddfptfd.
     * @pbrbm in
     * tif dontfnts of tif DER fndoding of tif bttributf sft.
     *
     * @fxdfption IOExdfption
     * on i/o frror, fndoding syntbx frror, unbddfptbblf or
     * unsupportfd bttributf, or duplidbtf bttributf.
     *
     * @sff PKCS9Attributf
     */
    publid PKCS9Attributfs(ObjfdtIdfntififr[] pfrmittfdAttributfs,
                           DfrInputStrfbm in) tirows IOExdfption {
        if (pfrmittfdAttributfs != null) {
            tiis.pfrmittfdAttributfs =
                nfw Hbsitbblf<ObjfdtIdfntififr, ObjfdtIdfntififr>(
                                                pfrmittfdAttributfs.lfngti);

            for (int i = 0; i < pfrmittfdAttributfs.lfngti; i++)
                tiis.pfrmittfdAttributfs.put(pfrmittfdAttributfs[i],
                                             pfrmittfdAttributfs[i]);
        } flsf {
            tiis.pfrmittfdAttributfs = null;
        }

        // dfrEndoding initiblizfd in <dodf>dfdodf()</dodf>
        dfrEndoding = dfdodf(in);
    }

    /**
     * Construdt b sft of PKCS9 Attributfs from tif dontfnts of its
     * DER fndoding on b DfrInputStrfbm.  Addfpt bll bttributfs
     * supportfd by dlbss PKCS9Attributf bnd rfjfdt bny unsupportfd
     * bttributfs.
     *
     * @pbrbm in tif dontfnts of tif DER fndoding of tif bttributf sft.
     * @fxdfption IOExdfption
     * on i/o frror, fndoding syntbx frror, or unsupportfd or
     * duplidbtf bttributf.
     *
     * @sff PKCS9Attributf
     */
    publid PKCS9Attributfs(DfrInputStrfbm in) tirows IOExdfption {
        tiis(in, fblsf);
    }

    /**
     * Construdt b sft of PKCS9 Attributfs from tif dontfnts of its
     * DER fndoding on b DfrInputStrfbm.  Addfpt bll bttributfs
     * supportfd by dlbss PKCS9Attributf bnd ignorf bny unsupportfd
     * bttributfs, if dirfdtfd.
     *
     * @pbrbm in tif dontfnts of tif DER fndoding of tif bttributf sft.
     * @pbrbm ignorfUnsupportfdAttributfs If truf tifn bny bttributfs
     * not supportfd by tif PKCS9Attributf dlbss brf ignorfd. Otifrwisf
     * unsupportfd bttributfs dbusf bn fxdfption to bf tirown.
     * @fxdfption IOExdfption
     * on i/o frror, fndoding syntbx frror, or unsupportfd or
     * duplidbtf bttributf.
     *
     * @sff PKCS9Attributf
     */
    publid PKCS9Attributfs(DfrInputStrfbm in,
        boolfbn ignorfUnsupportfdAttributfs) tirows IOExdfption {

        tiis.ignorfUnsupportfdAttributfs = ignorfUnsupportfdAttributfs;
        // dfrEndoding initiblizfd in <dodf>dfdodf()</dodf>
        dfrEndoding = dfdodf(in);
        pfrmittfdAttributfs = null;
    }

    /**
     * Construdt b sft of PKCS9 Attributfs from tif givfn brrby of
     * PKCS9 bttributfs.
     * DER fndoding on b DfrInputStrfbm.  All bttributfs in
     * <dodf>bttribs</dodf> must bf
     * supportfd by dlbss PKCS9Attributf.
     *
     * @fxdfption IOExdfption
     * on i/o frror, fndoding syntbx frror, or unsupportfd or
     * duplidbtf bttributf.
     *
     * @sff PKCS9Attributf
     */
    publid PKCS9Attributfs(PKCS9Attributf[] bttribs)
    tirows IllfgblArgumfntExdfption, IOExdfption {
        ObjfdtIdfntififr oid;
        for (int i=0; i < bttribs.lfngti; i++) {
            oid = bttribs[i].gftOID();
            if (bttributfs.dontbinsKfy(oid))
                tirow nfw IllfgblArgumfntExdfption(
                          "PKCSAttributf " + bttribs[i].gftOID() +
                          " duplidbtfd wiilf donstrudting " +
                          "PKCS9Attributfs.");

            bttributfs.put(oid, bttribs[i]);
        }
        dfrEndoding = gfnfrbtfDfrEndoding();
        pfrmittfdAttributfs = null;
    }


    /**
     * Dfdodf tiis sft of PKCS9 bttributfs from tif dontfnts of its
     * DER fndoding. Ignorfs unsupportfd bttributfs wifn dirfdtfd.
     *
     * @pbrbm in
     * tif dontfnts of tif DER fndoding of tif bttributf sft.
     *
     * @fxdfption IOExdfption
     * on i/o frror, fndoding syntbx frror, unbddfptbblf or
     * unsupportfd bttributf, or duplidbtf bttributf.
     */
    privbtf bytf[] dfdodf(DfrInputStrfbm in) tirows IOExdfption {

        DfrVbluf vbl = in.gftDfrVbluf();

        // sbvf tif DER fndoding witi its propfr tbg bytf.
        bytf[] dfrEndoding = vbl.toBytfArrby();
        dfrEndoding[0] = DfrVbluf.tbg_SftOf;

        DfrInputStrfbm dfrIn = nfw DfrInputStrfbm(dfrEndoding);
        DfrVbluf[] dfrVbls = dfrIn.gftSft(3,truf);

        PKCS9Attributf bttrib;
        ObjfdtIdfntififr oid;
        boolfbn rfusfEndoding = truf;

        for (int i=0; i < dfrVbls.lfngti; i++) {

            try {
                bttrib = nfw PKCS9Attributf(dfrVbls[i]);

            } dbtdi (PbrsingExdfption f) {
                if (ignorfUnsupportfdAttributfs) {
                    rfusfEndoding = fblsf; // dbnnot rfusf supplifd DER fndoding
                    dontinuf; // skip
                } flsf {
                    tirow f;
                }
            }
            oid = bttrib.gftOID();

            if (bttributfs.gft(oid) != null)
                tirow nfw IOExdfption("Duplidbtf PKCS9 bttributf: " + oid);

            if (pfrmittfdAttributfs != null &&
                !pfrmittfdAttributfs.dontbinsKfy(oid))
                tirow nfw IOExdfption("Attributf " + oid +
                                      " not pfrmittfd in tiis bttributf sft");

            bttributfs.put(oid, bttrib);
        }
        rfturn rfusfEndoding ? dfrEndoding : gfnfrbtfDfrEndoding();
    }

    /**
     * Put tif DER fndoding of tiis PKCS9 bttributf sft on bn
     * DfrOutputStrfbm, tbggfd witi tif givfn implidit tbg.
     *
     * @pbrbm tbg tif implidit tbg to usf in tif DER fndoding.
     * @pbrbm out tif output strfbm on wiidi to put tif DER fndoding.
     *
     * @fxdfption IOExdfption  on output frror.
     */
    publid void fndodf(bytf tbg, OutputStrfbm out) tirows IOExdfption {
        out.writf(tbg);
        out.writf(dfrEndoding, 1, dfrEndoding.lfngti -1);
    }

    privbtf bytf[] gfnfrbtfDfrEndoding() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        Objfdt[] bttribVbls = bttributfs.vblufs().toArrby();

        out.putOrdfrfdSftOf(DfrVbluf.tbg_SftOf,
                            dbstToDfrEndodfr(bttribVbls));
        rfturn out.toBytfArrby();
    }

    /**
     * Rfturn tif DER fndoding of tiis bttributf sft, tbggfd witi
     * DfrVbluf.tbg_SftOf.
     */
    publid bytf[] gftDfrEndoding() tirows IOExdfption {
        rfturn dfrEndoding.dlonf();

    }

    /**
     * Gft bn bttributf from tiis sft.
     */
    publid PKCS9Attributf gftAttributf(ObjfdtIdfntififr oid) {
        rfturn bttributfs.gft(oid);
    }

    /**
     * Gft bn bttributf from tiis sft.
     */
    publid PKCS9Attributf gftAttributf(String nbmf) {
        rfturn bttributfs.gft(PKCS9Attributf.gftOID(nbmf));
    }


    /**
     * Gft bn brrby of bll bttributfs in tiis sft, in ordfr of OID.
     */
    publid PKCS9Attributf[] gftAttributfs() {
        PKCS9Attributf[] bttribs = nfw PKCS9Attributf[bttributfs.sizf()];
        ObjfdtIdfntififr oid;

        int j = 0;
        for (int i=1; i < PKCS9Attributf.PKCS9_OIDS.lfngti &&
                      j < bttribs.lfngti; i++) {
            bttribs[j] = gftAttributf(PKCS9Attributf.PKCS9_OIDS[i]);

            if (bttribs[j] != null)
                j++;
        }
        rfturn bttribs;
    }

    /**
     * Gft bn bttributf vbluf by OID.
     */
    publid Objfdt gftAttributfVbluf(ObjfdtIdfntififr oid)
    tirows IOExdfption {
        try {
            Objfdt vbluf = gftAttributf(oid).gftVbluf();
            rfturn vbluf;
        } dbtdi (NullPointfrExdfption fx) {
            tirow nfw IOExdfption("No vbluf found for bttributf " + oid);
        }

    }

    /**
     *  Gft bn bttributf vbluf by typf nbmf.
     */
    publid Objfdt gftAttributfVbluf(String nbmf) tirows IOExdfption {
        ObjfdtIdfntififr oid = PKCS9Attributf.gftOID(nbmf);

        if (oid == null)
            tirow nfw IOExdfption("Attributf nbmf " + nbmf +
                                  " not rfdognizfd or not supportfd.");

        rfturn gftAttributfVbluf(oid);
    }


    /**
     * Rfturns tif PKCS9 blodk in b printbblf string form.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(200);
        sb.bppfnd("PKCS9 Attributfs: [\n\t");

        ObjfdtIdfntififr oid;
        PKCS9Attributf vbluf;

        boolfbn first = truf;
        for (int i = 1; i < PKCS9Attributf.PKCS9_OIDS.lfngti; i++) {
            vbluf = gftAttributf(PKCS9Attributf.PKCS9_OIDS[i]);

            if (vbluf == null) dontinuf;

            // wf ibvf b vbluf; print it
            if (first)
                first = fblsf;
            flsf
                sb.bppfnd(";\n\t");

            sb.bppfnd(vbluf.toString());
        }

        sb.bppfnd("\n\t] (fnd PKCS9 Attributfs)");

        rfturn sb.toString();
    }

    /**
     * Cbst bn objfdt brrby wiosf domponfnts brf
     * <dodf>DfrEndodfr</dodf>s to <dodf>DfrEndodfr[]</dodf>.
     */
    stbtid DfrEndodfr[] dbstToDfrEndodfr(Objfdt[] objs) {

        DfrEndodfr[] fndodfrs = nfw DfrEndodfr[objs.lfngti];

        for (int i=0; i < fndodfrs.lfngti; i++)
            fndodfrs[i] = (DfrEndodfr) objs[i];

        rfturn fndodfrs;
    }
}
