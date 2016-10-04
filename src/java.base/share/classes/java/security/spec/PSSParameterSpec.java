/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.spfd;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd;

/**
 * Tiis dlbss spfdififs b pbrbmftfr spfd for RSA-PSS signbturf sdifmf,
 * bs dffinfd in tif
 * <b irff="ittp://www.iftf.org/rfd/rfd3447.txt">PKCS#1 v2.1</b>
 * stbndbrd.
 *
 * <p>Its ASN.1 dffinition in PKCS#1 stbndbrd is dfsdribfd bflow:
 * <prf>
 * RSASSA-PSS-pbrbms ::= SEQUENCE {
 *   ibsiAlgoritim      [0] OAEP-PSSDigfstAlgoritims  DEFAULT sib1,
 *   mbskGfnAlgoritim   [1] PKCS1MGFAlgoritims  DEFAULT mgf1SHA1,
 *   sbltLfngti         [2] INTEGER  DEFAULT 20,
 *   trbilfrFifld       [3] INTEGER  DEFAULT 1
 * }
 * </prf>
 * wifrf
 * <prf>
 * OAEP-PSSDigfstAlgoritims    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-sib1 PARAMETERS NULL   }|
 *   { OID id-sib224 PARAMETERS NULL   }|
 *   { OID id-sib256 PARAMETERS NULL }|
 *   { OID id-sib384 PARAMETERS NULL }|
 *   { OID id-sib512 PARAMETERS NULL },
 *   ...  -- Allows for futurf fxpbnsion --
 * }
 *
 * PKCS1MGFAlgoritims    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-mgf1 PARAMETERS OAEP-PSSDigfstAlgoritims },
 *   ...  -- Allows for futurf fxpbnsion --
 * }
 * </prf>
 * <p>Notf: tif PSSPbrbmftfrSpfd.DEFAULT usfs tif following:
 *     mfssbgf digfst  -- "SHA-1"
 *     mbsk gfnfrbtion fundtion (mgf) -- "MGF1"
 *     pbrbmftfrs for mgf -- MGF1PbrbmftfrSpfd.SHA1
 *     SbltLfngti   -- 20
 *     TrbilfrFifld -- 1
 *
 * @sff MGF1PbrbmftfrSpfd
 * @sff AlgoritimPbrbmftfrSpfd
 * @sff jbvb.sfdurity.Signbturf
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sindf 1.4
 */

publid dlbss PSSPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf String mdNbmf = "SHA-1";
    privbtf String mgfNbmf = "MGF1";
    privbtf AlgoritimPbrbmftfrSpfd mgfSpfd = MGF1PbrbmftfrSpfd.SHA1;
    privbtf int sbltLfn = 20;
    privbtf int trbilfrFifld = 1;

    /**
     * Tif PSS pbrbmftfr sft witi bll dffbult vblufs.
     * @sindf 1.5
     */
    publid stbtid finbl PSSPbrbmftfrSpfd DEFAULT = nfw PSSPbrbmftfrSpfd();

    /**
     * Construdts b nfw {@dodf PSSPbrbmftfrSpfd} bs dffinfd in
     * tif PKCS #1 stbndbrd using tif dffbult vblufs.
     */
    privbtf PSSPbrbmftfrSpfd() {
    }

    /**
     * Crfbtfs b nfw {@dodf PSSPbrbmftfrSpfd} bs dffinfd in
     * tif PKCS #1 stbndbrd using tif spfdififd mfssbgf digfst,
     * mbsk gfnfrbtion fundtion, pbrbmftfrs for mbsk gfnfrbtion
     * fundtion, sblt lfngti, bnd trbilfr fifld vblufs.
     *
     * @pbrbm mdNbmf tif blgoritim nbmf of tif ibsi fundtion.
     * @pbrbm mgfNbmf tif blgoritim nbmf of tif mbsk gfnfrbtion
     * fundtion.
     * @pbrbm mgfSpfd tif pbrbmftfrs for tif mbsk gfnfrbtion
     * fundtion. If null is spfdififd, null will bf rfturnfd by
     * gftMGFPbrbmftfrs().
     * @pbrbm sbltLfn tif lfngti of sblt.
     * @pbrbm trbilfrFifld tif vbluf of tif trbilfr fifld.
     * @fxdfption NullPointfrExdfption if {@dodf mdNbmf},
     * or {@dodf mgfNbmf} is null.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sbltLfn}
     * or {@dodf trbilfrFifld} is lfss tibn 0.
     * @sindf 1.5
     */
    publid PSSPbrbmftfrSpfd(String mdNbmf, String mgfNbmf,
                            AlgoritimPbrbmftfrSpfd mgfSpfd,
                            int sbltLfn, int trbilfrFifld) {
        if (mdNbmf == null) {
            tirow nfw NullPointfrExdfption("digfst blgoritim is null");
        }
        if (mgfNbmf == null) {
            tirow nfw NullPointfrExdfption("mbsk gfnfrbtion fundtion " +
                                           "blgoritim is null");
        }
        if (sbltLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf sbltLfn vbluf: " +
                                               sbltLfn);
        }
        if (trbilfrFifld < 0) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf trbilfrFifld: " +
                                               trbilfrFifld);
        }
        tiis.mdNbmf = mdNbmf;
        tiis.mgfNbmf = mgfNbmf;
        tiis.mgfSpfd = mgfSpfd;
        tiis.sbltLfn = sbltLfn;
        tiis.trbilfrFifld = trbilfrFifld;
    }

    /**
     * Crfbtfs b nfw {@dodf PSSPbrbmftfrSpfd}
     * using tif spfdififd sblt lfngti bnd otifr dffbult vblufs bs
     * dffinfd in PKCS#1.
     *
     * @pbrbm sbltLfn tif lfngti of sblt in bits to bf usfd in PKCS#1
     * PSS fndoding.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sbltLfn} is
     * lfss tibn 0.
     */
    publid PSSPbrbmftfrSpfd(int sbltLfn) {
        if (sbltLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf sbltLfn vbluf: " +
                                               sbltLfn);
        }
        tiis.sbltLfn = sbltLfn;
    }

    /**
     * Rfturns tif mfssbgf digfst blgoritim nbmf.
     *
     * @rfturn tif mfssbgf digfst blgoritim nbmf.
     * @sindf 1.5
     */
    publid String gftDigfstAlgoritim() {
        rfturn mdNbmf;
    }

    /**
     * Rfturns tif mbsk gfnfrbtion fundtion blgoritim nbmf.
     *
     * @rfturn tif mbsk gfnfrbtion fundtion blgoritim nbmf.
     *
     * @sindf 1.5
     */
    publid String gftMGFAlgoritim() {
        rfturn mgfNbmf;
    }

    /**
     * Rfturns tif pbrbmftfrs for tif mbsk gfnfrbtion fundtion.
     *
     * @rfturn tif pbrbmftfrs for tif mbsk gfnfrbtion fundtion.
     * @sindf 1.5
     */
    publid AlgoritimPbrbmftfrSpfd gftMGFPbrbmftfrs() {
        rfturn mgfSpfd;
    }

    /**
     * Rfturns tif sblt lfngti in bits.
     *
     * @rfturn tif sblt lfngti.
     */
    publid int gftSbltLfngti() {
        rfturn sbltLfn;
    }

    /**
     * Rfturns tif vbluf for tif trbilfr fifld, i.f. bd in PKCS#1 v2.1.
     *
     * @rfturn tif vbluf for tif trbilfr fifld, i.f. bd in PKCS#1 v2.1.
     * @sindf 1.5
     */
    publid int gftTrbilfrFifld() {
        rfturn trbilfrFifld;
    }
}
