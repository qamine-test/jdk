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

pbdkbgf jbvb.sfdurity.spfd;

/**
 * A (trbnspbrfnt) spfdifidbtion of tif kfy mbtfribl
 * tibt donstitutfs b dryptogrbpiid kfy.
 *
 * <p>If tif kfy is storfd on b ibrdwbrf dfvidf, its
 * spfdifidbtion mby dontbin informbtion tibt iflps idfntify tif kfy on tif
 * dfvidf.
 *
 * <P> A kfy mby bf spfdififd in bn blgoritim-spfdifid wby, or in bn
 * blgoritim-indfpfndfnt fndoding formbt (sudi bs ASN.1).
 * For fxbmplf, b DSA privbtf kfy mby bf spfdififd by its domponfnts
 * {@dodf x}, {@dodf p}, {@dodf q}, bnd {@dodf g}
 * (sff {@link DSAPrivbtfKfySpfd}), or it mby bf
 * spfdififd using its DER fndoding
 * (sff {@link PKCS8EndodfdKfySpfd}).
 *
 * <P> Tiis intfrfbdf dontbins no mftiods or donstbnts. Its only purposf
 * is to group (bnd providf typf sbffty for) bll kfy spfdifidbtions.
 * All kfy spfdifidbtions must implfmfnt tiis intfrfbdf.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff EndodfdKfySpfd
 * @sff X509EndodfdKfySpfd
 * @sff PKCS8EndodfdKfySpfd
 * @sff DSAPrivbtfKfySpfd
 * @sff DSAPublidKfySpfd
 *
 * @sindf 1.2
 */

publid intfrfbdf KfySpfd { }
