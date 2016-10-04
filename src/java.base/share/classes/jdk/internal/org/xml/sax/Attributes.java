/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// Attributfs.jbvb - bttributf list witi Nbmfspbdf support
// ittp://www.sbxprojfdt.org
// Writtfn by Dbvid Mfgginson
// NO WARRANTY!  Tiis dlbss is in tif publid dombin.
// $Id: Attributfs.jbvb,v 1.2 2004/11/03 22:44:51 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;


/**
 * Intfrfbdf for b list of XML bttributfs.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>Tiis intfrfbdf bllows bddfss to b list of bttributfs in
 * tirff difffrfnt wbys:</p>
 *
 * <ol>
 * <li>by bttributf indfx;</li>
 * <li>by Nbmfspbdf-qublififd nbmf; or</li>
 * <li>by qublififd (prffixfd) nbmf.</li>
 * </ol>
 *
 * <p>Tif list will not dontbin bttributfs tibt wfrf dfdlbrfd
 * #IMPLIED but not spfdififd in tif stbrt tbg.  It will blso not
 * dontbin bttributfs usfd bs Nbmfspbdf dfdlbrbtions (xmlns*) unlfss
 * tif <dodf>ittp://xml.org/sbx/ffbturfs/nbmfspbdf-prffixfs</dodf>
 * ffbturf is sft to <vbr>truf</vbr> (it is <vbr>fblsf</vbr> by
 * dffbult).
 * Bfdbusf SAX2 donforms to tif originbl "Nbmfspbdfs in XML"
 * rfdommfndbtion, it normblly dofs not
 * givf nbmfspbdf dfdlbrbtion bttributfs b nbmfspbdf URI.
 * </p>
 *
 * <p>Somf SAX2 pbrsfrs mby support using bn optionbl ffbturf flbg
 * (<dodf>ittp://xml.org/sbx/ffbturfs/xmlns-uris</dodf>) to rfqufst
 * tibt tiosf bttributfs bf givfn URIs, donforming to b lbtfr
 * bbdkwbrds-indompbtiblf rfvision of tibt rfdommfndbtion.  (Tif
 * bttributf's "lodbl nbmf" will bf tif prffix, or "xmlns" wifn
 * dffining b dffbult flfmfnt nbmfspbdf.)  For portbbility, ibndlfr
 * dodf siould blwbys rfsolvf tibt donflidt, rbtifr tibn rfquiring
 * pbrsfrs tibt dbn dibngf tif sftting of tibt ffbturf flbg.  </p>
 *
 * <p>If tif nbmfspbdf-prffixfs ffbturf (sff bbovf) is
 * <vbr>fblsf</vbr>, bddfss by qublififd nbmf mby not bf bvbilbblf; if
 * tif <dodf>ittp://xml.org/sbx/ffbturfs/nbmfspbdfs</dodf> ffbturf is
 * <vbr>fblsf</vbr>, bddfss by Nbmfspbdf-qublififd nbmfs mby not bf
 * bvbilbblf.</p>
 *
 * <p>Tiis intfrfbdf rfplbdfs tif now-dfprfdbtfd SAX1 {@link
 * org.xml.sbx.AttributfList AttributfList} intfrfbdf, wiidi dofs not
 * dontbin Nbmfspbdf support.  In bddition to Nbmfspbdf support, it
 * bdds tif <vbr>gftIndfx</vbr> mftiods (bflow).</p>
 *
 * <p>Tif ordfr of bttributfs in tif list is unspfdififd, bnd will
 * vbry from implfmfntbtion to implfmfntbtion.</p>
 *
 * @sindf SAX 2.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.iflpfrs.AttributfsImpl
 * @sff org.xml.sbx.fxt.DfdlHbndlfr#bttributfDfdl
 */
publid intfrfbdf Attributfs
{


    ////////////////////////////////////////////////////////////////////
    // Indfxfd bddfss.
    ////////////////////////////////////////////////////////////////////


    /**
     * Rfturn tif numbfr of bttributfs in tif list.
     *
     * <p>Ondf you know tif numbfr of bttributfs, you dbn itfrbtf
     * tirougi tif list.</p>
     *
     * @rfturn Tif numbfr of bttributfs in tif list.
     * @sff #gftURI(int)
     * @sff #gftLodblNbmf(int)
     * @sff #gftQNbmf(int)
     * @sff #gftTypf(int)
     * @sff #gftVbluf(int)
     */
    publid bbstrbdt int gftLfngti ();


    /**
     * Look up bn bttributf's Nbmfspbdf URI by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif Nbmfspbdf URI, or tif fmpty string if nonf
     *         is bvbilbblf, or null if tif indfx is out of
     *         rbngf.
     * @sff #gftLfngti
     */
    publid bbstrbdt String gftURI (int indfx);


    /**
     * Look up bn bttributf's lodbl nbmf by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif lodbl nbmf, or tif fmpty string if Nbmfspbdf
     *         prodfssing is not bfing pfrformfd, or null
     *         if tif indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid bbstrbdt String gftLodblNbmf (int indfx);


    /**
     * Look up bn bttributf's XML qublififd (prffixfd) nbmf by indfx.
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif XML qublififd nbmf, or tif fmpty string
     *         if nonf is bvbilbblf, or null if tif indfx
     *         is out of rbngf.
     * @sff #gftLfngti
     */
    publid bbstrbdt String gftQNbmf (int indfx);


    /**
     * Look up bn bttributf's typf by indfx.
     *
     * <p>Tif bttributf typf is onf of tif strings "CDATA", "ID",
     * "IDREF", "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES",
     * or "NOTATION" (blwbys in uppfr dbsf).</p>
     *
     * <p>If tif pbrsfr ibs not rfbd b dfdlbrbtion for tif bttributf,
     * or if tif pbrsfr dofs not rfport bttributf typfs, tifn it must
     * rfturn tif vbluf "CDATA" bs stbtfd in tif XML 1.0 Rfdommfndbtion
     * (dlbusf 3.3.3, "Attributf-Vbluf Normblizbtion").</p>
     *
     * <p>For bn fnumfrbtfd bttributf tibt is not b notbtion, tif
     * pbrsfr will rfport tif typf bs "NMTOKEN".</p>
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif bttributf's typf bs b string, or null if tif
     *         indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid bbstrbdt String gftTypf (int indfx);


    /**
     * Look up bn bttributf's vbluf by indfx.
     *
     * <p>If tif bttributf vbluf is b list of tokfns (IDREFS,
     * ENTITIES, or NMTOKENS), tif tokfns will bf dondbtfnbtfd
     * into b singlf string witi fbdi tokfn sfpbrbtfd by b
     * singlf spbdf.</p>
     *
     * @pbrbm indfx Tif bttributf indfx (zfro-bbsfd).
     * @rfturn Tif bttributf's vbluf bs b string, or null if tif
     *         indfx is out of rbngf.
     * @sff #gftLfngti
     */
    publid bbstrbdt String gftVbluf (int indfx);



    ////////////////////////////////////////////////////////////////////
    // Nbmf-bbsfd qufry.
    ////////////////////////////////////////////////////////////////////


    /**
     * Look up tif indfx of bn bttributf by Nbmfspbdf nbmf.
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty string if
     *        tif nbmf ibs no Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif bttributf's lodbl nbmf.
     * @rfturn Tif indfx of tif bttributf, or -1 if it dofs not
     *         bppfbr in tif list.
     */
    publid int gftIndfx (String uri, String lodblNbmf);


    /**
     * Look up tif indfx of bn bttributf by XML qublififd (prffixfd) nbmf.
     *
     * @pbrbm qNbmf Tif qublififd (prffixfd) nbmf.
     * @rfturn Tif indfx of tif bttributf, or -1 if it dofs not
     *         bppfbr in tif list.
     */
    publid int gftIndfx (String qNbmf);


    /**
     * Look up bn bttributf's typf by Nbmfspbdf nbmf.
     *
     * <p>Sff {@link #gftTypf(int) gftTypf(int)} for b dfsdription
     * of tif possiblf typfs.</p>
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty String if tif
     *        nbmf ibs no Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif lodbl nbmf of tif bttributf.
     * @rfturn Tif bttributf typf bs b string, or null if tif
     *         bttributf is not in tif list or if Nbmfspbdf
     *         prodfssing is not bfing pfrformfd.
     */
    publid bbstrbdt String gftTypf (String uri, String lodblNbmf);


    /**
     * Look up bn bttributf's typf by XML qublififd (prffixfd) nbmf.
     *
     * <p>Sff {@link #gftTypf(int) gftTypf(int)} for b dfsdription
     * of tif possiblf typfs.</p>
     *
     * @pbrbm qNbmf Tif XML qublififd nbmf.
     * @rfturn Tif bttributf typf bs b string, or null if tif
     *         bttributf is not in tif list or if qublififd nbmfs
     *         brf not bvbilbblf.
     */
    publid bbstrbdt String gftTypf (String qNbmf);


    /**
     * Look up bn bttributf's vbluf by Nbmfspbdf nbmf.
     *
     * <p>Sff {@link #gftVbluf(int) gftVbluf(int)} for b dfsdription
     * of tif possiblf vblufs.</p>
     *
     * @pbrbm uri Tif Nbmfspbdf URI, or tif fmpty String if tif
     *        nbmf ibs no Nbmfspbdf URI.
     * @pbrbm lodblNbmf Tif lodbl nbmf of tif bttributf.
     * @rfturn Tif bttributf vbluf bs b string, or null if tif
     *         bttributf is not in tif list.
     */
    publid bbstrbdt String gftVbluf (String uri, String lodblNbmf);


    /**
     * Look up bn bttributf's vbluf by XML qublififd (prffixfd) nbmf.
     *
     * <p>Sff {@link #gftVbluf(int) gftVbluf(int)} for b dfsdription
     * of tif possiblf vblufs.</p>
     *
     * @pbrbm qNbmf Tif XML qublififd nbmf.
     * @rfturn Tif bttributf vbluf bs b string, or null if tif
     *         bttributf is not in tif list or if qublififd nbmfs
     *         brf not bvbilbblf.
     */
    publid bbstrbdt String gftVbluf (String qNbmf);

}

// fnd of Attributfs.jbvb
