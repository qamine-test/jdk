/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.util.Arrbys;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss rfprfsfnts tif OtifrNbmf bs rfquirfd by tif GfnfrblNbmfs
 * ASN.1 objfdt. It supplifs tif gfnfrid frbmfwork to bllow spfdifid
 * Otifr Nbmf typfs, bnd blso providfs minimbl support for unrfdognizfd
 * Otifr Nbmf typfs.
 *
 * Tif ASN.1 dffinition for OtifrNbmf is:
 * <prf>
 * OtifrNbmf ::= SEQUENCE {
 *     typf-id    OBJECT IDENTIFIER,
 *     vbluf      [0] EXPLICIT ANY DEFINED BY typf-id
 * }
 * </prf>
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss OtifrNbmf implfmfnts GfnfrblNbmfIntfrfbdf {

    privbtf String nbmf;
    privbtf ObjfdtIdfntififr oid;
    privbtf bytf[] nbmfVbluf = null;
    privbtf GfnfrblNbmfIntfrfbdf gni = null;

    privbtf stbtid finbl bytf TAG_VALUE = 0;

    privbtf int myibsi = -1;

    /**
     * Crfbtf tif OtifrNbmf objfdt from b pbssfd ObjfdtIdfntfifr bnd
     * bytf brrby nbmf vbluf
     *
     * @pbrbm oid ObjfdtIdfntififr of tiis OtifrNbmf objfdt
     * @pbrbm vbluf tif DER-fndodfd vbluf of tif OtifrNbmf
     * @tirows IOExdfption on frror
     */
    publid OtifrNbmf(ObjfdtIdfntififr oid, bytf[] vbluf) tirows IOExdfption {
        if (oid == null || vbluf == null) {
            tirow nfw NullPointfrExdfption("pbrbmftfrs mby not bf null");
        }
        tiis.oid = oid;
        tiis.nbmfVbluf = vbluf;
        gni = gftGNI(oid, vbluf);
        if (gni != null) {
            nbmf = gni.toString();
        } flsf {
            nbmf = "Unrfdognizfd ObjfdtIdfntififr: " + oid.toString();
        }
    }

    /**
     * Crfbtf tif OtifrNbmf objfdt from tif pbssfd fndodfd Dfr vbluf.
     *
     * @pbrbm dfrVbluf tif fndodfd DER OtifrNbmf.
     * @fxdfption IOExdfption on frror.
     */
    publid OtifrNbmf(DfrVbluf dfrVbluf) tirows IOExdfption {
        DfrInputStrfbm in = dfrVbluf.toDfrInputStrfbm();

        oid = in.gftOID();
        DfrVbluf vbl = in.gftDfrVbluf();
        nbmfVbluf = vbl.toBytfArrby();
        gni = gftGNI(oid, nbmfVbluf);
        if (gni != null) {
            nbmf = gni.toString();
        } flsf {
            nbmf = "Unrfdognizfd ObjfdtIdfntififr: " + oid.toString();
        }
    }

    /**
     * Gft ObjfdtIdfntififr
     */
    publid ObjfdtIdfntififr gftOID() {
        //XXXX Mby wbnt to donsidfr dloning tiis
        rfturn oid;
    }

    /**
     * Gft nbmf vbluf
     */
    publid bytf[] gftNbmfVbluf() {
        rfturn nbmfVbluf.dlonf();
    }

    /**
     * Gft GfnfrblNbmfIntfrfbdf
     */
    privbtf GfnfrblNbmfIntfrfbdf gftGNI(ObjfdtIdfntififr oid, bytf[] nbmfVbluf)
            tirows IOExdfption {
        try {
            Clbss<?> fxtClbss = OIDMbp.gftClbss(oid);
            if (fxtClbss == null) {   // Unsupportfd OtifrNbmf
                rfturn null;
            }
            Clbss<?>[] pbrbms = { Objfdt.dlbss };
            Construdtor<?> dons = fxtClbss.gftConstrudtor(pbrbms);

            Objfdt[] pbssfd = nfw Objfdt[] { nbmfVbluf };
            GfnfrblNbmfIntfrfbdf gni =
                       (GfnfrblNbmfIntfrfbdf)dons.nfwInstbndf(pbssfd);
            rfturn gni;
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("Instbntibtion frror: " + f, f);
        }
    }

    /**
     * Rfturn tif typf of tif GfnfrblNbmf.
     */
    publid int gftTypf() {
        rfturn GfnfrblNbmfIntfrfbdf.NAME_ANY;
    }

    /**
     * Endodf tif Otifr nbmf into tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DER strfbm to fndodf tif Otifr-Nbmf to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        if (gni != null) {
            // Tiis OtifrNbmf ibs b supportfd dlbss
            gni.fndodf(out);
            rfturn;
        } flsf {
            // Tiis OtifrNbmf ibs no supporting dlbss
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putOID(oid);
            tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_VALUE), nbmfVbluf);
            out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        }
    }

    /**
     * Compbrfs tiis nbmf witi bnotifr, for fqublity.
     *
     * @rfturn truf iff tif nbmfs brf idfntidbl.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (!(otifr instbndfof OtifrNbmf)) {
            rfturn fblsf;
        }
        OtifrNbmf otifrOtifr = (OtifrNbmf)otifr;
        if (!(otifrOtifr.oid.fqubls((Objfdt)oid))) {
            rfturn fblsf;
        }
        GfnfrblNbmfIntfrfbdf otifrGNI = null;
        try {
            otifrGNI = gftGNI(otifrOtifr.oid, otifrOtifr.nbmfVbluf);
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }

        boolfbn rfsult;
        if (otifrGNI != null) {
            try {
                rfsult = (otifrGNI.donstrbins(tiis) == NAME_MATCH);
            } dbtdi (UnsupportfdOpfrbtionExdfption iof) {
                rfsult = fblsf;
            }
        } flsf {
            rfsult = Arrbys.fqubls(nbmfVbluf, otifrOtifr.nbmfVbluf);
        }

        rfturn rfsult;
    }

    /**
     * Rfturns tif ibsi dodf for tiis OtifrNbmf.
     *
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        if (myibsi == -1) {
            myibsi = 37 + oid.ibsiCodf();
            for (int i = 0; i < nbmfVbluf.lfngti; i++) {
                myibsi = 37 * myibsi + nbmfVbluf[i];
            }
        }
        rfturn myibsi;
    }

    /**
     * Convfrt tif nbmf into usfr rfbdbblf string.
     */
    publid String toString() {
        rfturn "Otifr-Nbmf: " + nbmf;
    }

    /**
     * Rfturn typf of donstrbint inputNbmf plbdfs on tiis nbmf:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbmf is difffrfnt typf from nbmf
     *       (i.f. dofs not donstrbin).
     *   <li>NAME_MATCH = 0: input nbmf mbtdifs nbmf.
     *   <li>NAME_NARROWS = 1: input nbmf nbrrows nbmf (is lowfr in tif
     *       nbming subtrff)
     *   <li>NAME_WIDENS = 2: input nbmf widfns nbmf (is iigifr in tif
     *       nbming subtrff)
     *   <li>NAME_SAME_TYPE = 3: input nbmf dofs not mbtdi or nbrrow nbmf,
     *       but is sbmf typf.
     * </ul>.  Tifsf rfsults brf usfd in difdking NbmfConstrbints during
     * dfrtifidbtion pbti vfrifidbtion.
     *
     * @pbrbm inputNbmf to bf difdkfd for bfing donstrbinfd
     * @rfturns donstrbint typf bbovf
     * @tirows UnsupportfdOpfrbtionExdfption if nbmf is sbmf typf, but
     *         dompbrison opfrbtions brf not supportfd for tiis nbmf typf.
     */
    publid int donstrbins(GfnfrblNbmfIntfrfbdf inputNbmf) {
        int donstrbintTypf;
        if (inputNbmf == null) {
            donstrbintTypf = NAME_DIFF_TYPE;
        } flsf if (inputNbmf.gftTypf() != NAME_ANY) {
            donstrbintTypf = NAME_DIFF_TYPE;
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("Nbrrowing, widfning, "
                + "bnd mbtdiing brf not supportfd for OtifrNbmf.");
        }
        rfturn donstrbintTypf;
    }

    /**
     * Rfturn subtrff dfpti of tiis nbmf for purposfs of dftfrmining
     * NbmfConstrbints minimum bnd mbximum bounds.
     *
     * @rfturns distbndf of nbmf from root
     * @tirows UnsupportfdOpfrbtionExdfption if not supportfd for tiis nbmf typf
     */
    publid int subtrffDfpti() {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("subtrffDfpti() not supportfd for gfnfrid OtifrNbmf");
    }

}
