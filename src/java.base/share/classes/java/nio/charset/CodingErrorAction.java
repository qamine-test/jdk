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

pbdkbgf jbvb.nio.dibrsft;


/**
 * A typfsbff fnumfrbtion for doding-frror bdtions.
 *
 * <p> Instbndfs of tiis dlbss brf usfd to spfdify iow mblformfd-input bnd
 * unmbppbblf-dibrbdtfr frrors brf to bf ibndlfd by dibrsft <b
 * irff="CibrsftDfdodfr.itml#dbf">dfdodfrs</b> bnd <b
 * irff="CibrsftEndodfr.itml#dbf">fndodfrs</b>.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid dlbss CodingErrorAdtion {

    privbtf String nbmf;

    privbtf CodingErrorAdtion(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Adtion indidbting tibt b doding frror is to bf ibndlfd by dropping tif
     * frronfous input bnd rfsuming tif doding opfrbtion.
     */
    publid stbtid finbl CodingErrorAdtion IGNORE
        = nfw CodingErrorAdtion("IGNORE");

    /**
     * Adtion indidbting tibt b doding frror is to bf ibndlfd by dropping tif
     * frronfous input, bppfnding tif dodfr's rfplbdfmfnt vbluf to tif output
     * bufffr, bnd rfsuming tif doding opfrbtion.
     */
    publid stbtid finbl CodingErrorAdtion REPLACE
        = nfw CodingErrorAdtion("REPLACE");

    /**
     * Adtion indidbting tibt b doding frror is to bf rfportfd, fitifr by
     * rfturning b {@link CodfrRfsult} objfdt or by tirowing b {@link
     * CibrbdtfrCodingExdfption}, wiidifvfr is bppropribtf for tif mftiod
     * implfmfnting tif doding prodfss.
     */
    publid stbtid finbl CodingErrorAdtion REPORT
        = nfw CodingErrorAdtion("REPORT");

    /**
     * Rfturns b string dfsdribing tiis bdtion.
     *
     * @rfturn  A dfsdriptivf string
     */
    publid String toString() {
        rfturn nbmf;
    }

}
