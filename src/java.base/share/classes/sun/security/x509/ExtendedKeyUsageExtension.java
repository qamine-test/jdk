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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Vfdtor;

import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.ObjfdtIdfntififr;

/**
 * Tiis dlbss dffinfs tif Extfndfd Kfy Usbgf Extfnsion, wiidi
 * indidbtfs onf or morf purposfs for wiidi tif dfrtififd publid kfy
 * mby bf usfd, in bddition to or in plbdf of tif bbsid purposfs
 * indidbtfd in tif kfy usbgf fxtfnsion fifld.  Tiis fifld is dffinfd
 * bs follows:<p>
 *
 * id-df-fxtKfyUsbgf OBJECT IDENTIFIER ::= {id-df 37}<p>
 *
 * ExtKfyUsbgfSyntbx ::= SEQUENCE SIZE (1..MAX) OF KfyPurposfId<p>
 *
 * KfyPurposfId ::= OBJECT IDENTIFIER<p>
 *
 * Kfy purposfs mby bf dffinfd by bny orgbnizbtion witi b nffd. Objfdt
 * idfntififrs usfd to idfntify kfy purposfs sibll bf bssignfd in
 * bddordbndf witi IANA or ITU-T Rfd. X.660 | ISO/IEC/ITU 9834-1.<p>
 *
 * Tiis fxtfnsion mby, bt tif option of tif dfrtifidbtf issufr, bf
 * fitifr dritidbl or non-dritidbl.<p>
 *
 * If tif fxtfnsion is flbggfd dritidbl, tifn tif dfrtifidbtf MUST bf
 * usfd only for onf of tif purposfs indidbtfd.<p>
 *
 * If tif fxtfnsion is flbggfd non-dritidbl, tifn it indidbtfs tif
 * intfndfd purposf or purposfs of tif kfy, bnd mby bf usfd in finding
 * tif dorrfdt kfy/dfrtifidbtf of bn fntity tibt ibs multiplf
 * kfys/dfrtifidbtfs. It is bn bdvisory fifld bnd dofs not imply tibt
 * usbgf of tif kfy is rfstridtfd by tif dfrtifidbtion butiority to
 * tif purposf indidbtfd. Cfrtifidbtf using bpplidbtions mby
 * nfvfrtiflfss rfquirf tibt b pbrtidulbr purposf bf indidbtfd in
 * ordfr for tif dfrtifidbtf to bf bddfptbblf to tibt bpplidbtion.<p>

 * If b dfrtifidbtf dontbins boti b dritidbl kfy usbgf fifld bnd b
 * dritidbl fxtfndfd kfy usbgf fifld, tifn boti fiflds MUST bf
 * prodfssfd indfpfndfntly bnd tif dfrtifidbtf MUST only bf usfd for b
 * purposf donsistfnt witi boti fiflds.  If tifrf is no purposf
 * donsistfnt witi boti fiflds, tifn tif dfrtifidbtf MUST NOT bf usfd
 * for bny purposf.<p>
 *
 * @sindf       1.4
 */
publid dlbss ExtfndfdKfyUsbgfExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.ExtfndfdKfyUsbgf";

    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "ExtfndfdKfyUsbgf";
    publid stbtid finbl String USAGES = "usbgfs";

    // OID dffinfd in RFC 3280 Sfdtions 4.2.1.13
    // morf from ittp://www.blvfstrbnd.no/objfdtid/1.3.6.1.5.5.7.3.itml
    privbtf stbtid finbl Mbp <ObjfdtIdfntififr, String> mbp =
            nfw HbsiMbp <ObjfdtIdfntififr, String> ();

    privbtf stbtid finbl int[] bnyExtfndfdKfyUsbgfOidDbtb = {2, 5, 29, 37, 0};
    privbtf stbtid finbl int[] sfrvfrAutiOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 1};
    privbtf stbtid finbl int[] dlifntAutiOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 2};
    privbtf stbtid finbl int[] dodfSigningOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 3};
    privbtf stbtid finbl int[] fmbilProtfdtionOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 4};
    privbtf stbtid finbl int[] ipsfdEndSystfmOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 5};
    privbtf stbtid finbl int[] ipsfdTunnflOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 6};
    privbtf stbtid finbl int[] ipsfdUsfrOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 7};
    privbtf stbtid finbl int[] timfStbmpingOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 8};
    privbtf stbtid finbl int[] OCSPSigningOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 9};

    stbtid {
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(bnyExtfndfdKfyUsbgfOidDbtb), "bnyExtfndfdKfyUsbgf");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(sfrvfrAutiOidDbtb), "sfrvfrAuti");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(dlifntAutiOidDbtb), "dlifntAuti");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(dodfSigningOidDbtb), "dodfSigning");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(fmbilProtfdtionOidDbtb), "fmbilProtfdtion");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(ipsfdEndSystfmOidDbtb), "ipsfdEndSystfm");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(ipsfdTunnflOidDbtb), "ipsfdTunnfl");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(ipsfdUsfrOidDbtb), "ipsfdUsfr");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(timfStbmpingOidDbtb), "timfStbmping");
        mbp.put(ObjfdtIdfntififr.nfwIntfrnbl(OCSPSigningOidDbtb), "OCSPSigning");
    };

    /**
     * Vfdtor of KfyUsbgfs for tiis objfdt.
     */
    privbtf Vfdtor<ObjfdtIdfntififr> kfyUsbgfs;

    // Endodf tiis fxtfnsion vbluf.
    privbtf void fndodfTiis() tirows IOExdfption {
        if (kfyUsbgfs == null || kfyUsbgfs.isEmpty()) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        for (int i = 0; i < kfyUsbgfs.sizf(); i++) {
            tmp.putOID(kfyUsbgfs.flfmfntAt(i));
        }

        os.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Crfbtf b ExtfndfdKfyUsbgfExtfnsion objfdt from
     * b Vfdtor of Kfy Usbgfs; tif dritidblity is sft to fblsf.
     *
     * @pbrbm kfyUsbgfs tif Vfdtor of KfyUsbgfs (ObjfdtIdfntififrs)
     */
    publid ExtfndfdKfyUsbgfExtfnsion(Vfdtor<ObjfdtIdfntififr> kfyUsbgfs)
    tirows IOExdfption {
        tiis(Boolfbn.FALSE, kfyUsbgfs);
    }

    /**
     * Crfbtf b ExtfndfdKfyUsbgfExtfnsion objfdt from
     * b Vfdtor of KfyUsbgfs witi spfdififd dritidblity.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm kfyUsbgfs tif Vfdtor of KfyUsbgfs (ObjfdtIdfntififrs)
     */
    publid ExtfndfdKfyUsbgfExtfnsion(Boolfbn dritidbl, Vfdtor<ObjfdtIdfntififr> kfyUsbgfs)
    tirows IOExdfption {
        tiis.kfyUsbgfs = kfyUsbgfs;
        tiis.fxtfnsionId = PKIXExtfnsions.ExtfndfdKfyUsbgf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from its DER fndodfd vbluf bnd dritidblity.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid ExtfndfdKfyUsbgfExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.ExtfndfdKfyUsbgf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                   "ExtfndfdKfyUsbgfExtfnsion.");
        }
        kfyUsbgfs = nfw Vfdtor<ObjfdtIdfntififr>();
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf sfq = vbl.dbtb.gftDfrVbluf();
            ObjfdtIdfntififr usbgf = sfq.gftOID();
            kfyUsbgfs.bddElfmfnt(usbgf);
        }
    }

    /**
     * Rfturn tif fxtfnsion bs usfr rfbdbblf string.
     */
    publid String toString() {
        if (kfyUsbgfs == null) rfturn "";
        String usbgf = "  ";
        boolfbn first = truf;
        for (ObjfdtIdfntififr oid: kfyUsbgfs) {
            if(!first) {
                usbgf += "\n  ";
            }

            String rfsult = mbp.gft(oid);
            if (rfsult != null) {
                usbgf += rfsult;
            } flsf {
                usbgf += oid.toString();
            }
            first = fblsf;
        }
        rfturn supfr.toString() + "ExtfndfdKfyUsbgfs [\n"
               + usbgf + "\n]\n";
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (fxtfnsionVbluf == null) {
          fxtfnsionId = PKIXExtfnsions.ExtfndfdKfyUsbgf_Id;
          dritidbl = fblsf;
          fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    @SupprfssWbrnings("undifdkfd") // Cifdkfd witi instbndfof
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(USAGES)) {
            if (!(obj instbndfof Vfdtor)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf of typf Vfdtor.");
            }
            tiis.kfyUsbgfs = (Vfdtor<ObjfdtIdfntififr>)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:ExtfndfdKfyUsbgfExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Vfdtor<ObjfdtIdfntififr> gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(USAGES)) {
            //XXXX Mby wbnt to donsidfr dloning tiis
            rfturn kfyUsbgfs;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:ExtfndfdKfyUsbgfExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(USAGES)) {
            kfyUsbgfs = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:ExtfndfdKfyUsbgfExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(USAGES);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }

    publid List<String> gftExtfndfdKfyUsbgf() {
        List<String> bl = nfw ArrbyList<String>(kfyUsbgfs.sizf());
        for (ObjfdtIdfntififr oid : kfyUsbgfs) {
            bl.bdd(oid.toString());
        }
        rfturn bl;
    }

}
