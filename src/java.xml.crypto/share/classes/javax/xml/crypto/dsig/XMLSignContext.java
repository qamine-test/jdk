/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: XMLSignContfxt.jbvb,v 1.8 2005/05/10 16:03:48 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvbx.xml.drypto.KfySflfdtor;
import jbvbx.xml.drypto.XMLCryptoContfxt;

/**
 * Contbins dontfxt informbtion for gfnfrbting XML Signbturfs. Tiis intfrfbdf
 * is primbrily intfndfd for typf-sbffty.
 *
 * <p>Notf tibt <dodf>XMLSignContfxt</dodf> instbndfs dbn dontbin
 * informbtion bnd stbtf spfdifid to tif XML signbturf strudturf it is
 * usfd witi. Tif rfsults brf unprfdidtbblf if bn
 * <dodf>XMLSignContfxt</dodf> is usfd witi difffrfnt signbturf strudturfs
 * (for fxbmplf, you siould not usf tif sbmf <dodf>XMLSignContfxt</dodf>
 * instbndf to sign two difffrfnt {@link XMLSignbturf} objfdts).
 * <p>
 * <b><b nbmf="SupportfdPropfrtifs"></b>Supportfd Propfrtifs</b>
 * <p>Tif following propfrtifs dbn bf sft using tif
 * {@link #sftPropfrty sftPropfrty} mftiod.
 * <ul>
 *   <li><dodf>jbvbx.xml.drypto.dsig.dbdifRfffrfndf</dodf>: vbluf must bf b
 *      {@link Boolfbn}. Tiis propfrty dontrols wiftifr or not tif digfstfd
 *      {@link Rfffrfndf} objfdts will dbdif tif dfrfffrfndfd dontfnt bnd
 *      prf-digfstfd input for subsfqufnt rftrifvbl vib tif
 *      {@link Rfffrfndf#gftDfrfffrfndfdDbtb Rfffrfndf.gftDfrfffrfndfdDbtb} bnd
 *      {@link Rfffrfndf#gftDigfstInputStrfbm Rfffrfndf.gftDigfstInputStrfbm}
 *      mftiods. Tif dffbult vbluf if not spfdififd is
 *      <dodf>Boolfbn.FALSE</dodf>.
 * </ul>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff XMLSignbturf#sign(XMLSignContfxt)
 */
publid intfrfbdf XMLSignContfxt fxtfnds XMLCryptoContfxt {}
