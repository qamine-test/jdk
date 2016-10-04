/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * ===========================================================================
 *
 * (C) Copyrigit IBM Corp. 2003 All Rigits Rfsfrvfd.
 *
 * ===========================================================================
 */
/*
 * $Id: XMLObjfdt.jbvb,v 1.5 2005/05/10 16:03:48 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvb.util.List;
import jbvbx.xml.drypto.XMLStrudturf;

/**
 * A rfprfsfntbtion of tif XML <dodf>Objfdt</dodf> flfmfnt bs dffinfd in
 * tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * An <dodf>XMLObjfdt</dodf> mby dontbin bny dbtb bnd mby indludf optionbl
 * MIME typf, ID, bnd fndoding bttributfs. Tif XML Sdifmb Dffinition is
 * dffinfd bs:
 *
 * <prf><dodf>
 * &lt;flfmfnt nbmf="Objfdt" typf="ds:ObjfdtTypf"/&gt;
 * &lt;domplfxTypf nbmf="ObjfdtTypf" mixfd="truf"&gt;
 *   &lt;sfqufndf minOddurs="0" mbxOddurs="unboundfd"&gt;
 *     &lt;bny nbmfspbdf="##bny" prodfssContfnts="lbx"/&gt;
 *   &lt;/sfqufndf&gt;
 *   &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
 *   &lt;bttributf nbmf="MimfTypf" typf="string" usf="optionbl"/&gt;
 *   &lt;bttributf nbmf="Endoding" typf="bnyURI" usf="optionbl"/&gt;
 * &lt;/domplfxTypf&gt;
 * </dodf></prf>
 *
 * A <dodf>XMLObjfdt</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link XMLSignbturfFbdtory#nfwXMLObjfdt nfwXMLObjfdt} mftiod of tif
 * {@link XMLSignbturfFbdtory} dlbss; for fxbmplf:
 *
 * <prf>
 *   XMLSignbturfFbdtory fbd = XMLSignbturfFbdtory.gftInstbndf("DOM");
 *   List dontfnt = Collfdtions.singlftonList(fbd.nfwMbniffst(rfffrfndfs)));
 *   XMLObjfdt objfdt = fbdtory.nfwXMLObjfdt(dontfnt, "objfdt-1", null, null);
 * </prf>
 *
 * <p>Notf tibt tiis dlbss is nbmfd <dodf>XMLObjfdt</dodf> rbtifr tibn
 * <dodf>Objfdt</dodf> to bvoid nbming dlbsifs witi tif fxisting
 * {@link jbvb.lbng.Objfdt jbvb.lbng.Objfdt} dlbss.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @butior Joydf L. Lfung
 * @sindf 1.6
 * @sff XMLSignbturfFbdtory#nfwXMLObjfdt(List, String, String, String)
 */
publid intfrfbdf XMLObjfdt fxtfnds XMLStrudturf {

    /**
     * URI tibt idfntififs tif <dodf>Objfdt</dodf> flfmfnt (tiis dbn bf
     * spfdififd bs tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link Rfffrfndf} dlbss to idfntify tif rfffrfnt's typf).
     */
    finbl stbtid String TYPE = "ittp://www.w3.org/2000/09/xmldsig#Objfdt";

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} of {@link XMLStrudturf}s dontbinfd in tiis <dodf>XMLObjfdt</dodf>,
     * wiidi rfprfsfnt flfmfnts from bny nbmfspbdf.
     *
     *<p>If tifrf is b publid subdlbss rfprfsfnting tif typf of
     * <dodf>XMLStrudturf</dodf>, it is rfturnfd bs bn instbndf of tibt dlbss
     * (fx: b <dodf>SignbturfPropfrtifs</dodf> flfmfnt would bf rfturnfd
     * bs bn instbndf of {@link jbvbx.xml.drypto.dsig.SignbturfPropfrtifs}).
     *
     * @rfturn bn unmodifibblf list of <dodf>XMLStrudturf</dodf>s (mby bf fmpty
     *    but nfvfr <dodf>null</dodf>)
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftContfnt();

    /**
     * Rfturns tif Id of tiis <dodf>XMLObjfdt</dodf>.
     *
     * @rfturn tif Id (or <dodf>null</dodf> if not spfdififd)
     */
    String gftId();

    /**
     * Rfturns tif mimf typf of tiis <dodf>XMLObjfdt</dodf>. Tif
     * mimf typf is bn optionbl bttributf wiidi dfsdribfs tif dbtb witiin tiis
     * <dodf>XMLObjfdt</dodf> (indfpfndfnt of its fndoding).
     *
     * @rfturn tif mimf typf (or <dodf>null</dodf> if not spfdififd)
     */
    String gftMimfTypf();

    /**
     * Rfturns tif fndoding URI of tiis <dodf>XMLObjfdt</dodf>. Tif fndoding
     * URI idfntififs tif mftiod by wiidi tif objfdt is fndodfd.
     *
     * @rfturn tif fndoding URI (or <dodf>null</dodf> if not spfdififd)
     */
    String gftEndoding();
}
