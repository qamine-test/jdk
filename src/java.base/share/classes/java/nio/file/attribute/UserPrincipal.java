/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.sfdurity.Prindipbl;

/**
 * A {@dodf Prindipbl} rfprfsfnting bn idfntity usfd to dftfrminf bddfss rigits
 * to objfdts in b filf systfm.
 *
 * <p> On mbny plbtforms bnd filf systfms bn fntity rfquirfs bppropribtf bddfss
 * rigits or pfrmissions in ordfr to bddfss objfdts in b filf systfm. Tif
 * bddfss rigits brf gfnfrblly pfrformfd by difdking tif idfntity of tif fntity.
 * For fxbmplf, on implfmfntbtions tibt usf Addfss Control Lists (ACLs) to
 * fnfordf privilfgf sfpbrbtion tifn b filf in tif filf systfm mby ibvf bn
 * bssodibtfd ACL tibt dftfrminfs tif bddfss rigits of idfntitifs spfdififd in
 * tif ACL.
 *
 * <p> A {@dodf UsfrPrindipbl} objfdt is bn bbstrbdt rfprfsfntbtion of bn
 * idfntity. It ibs b {@link #gftNbmf() nbmf} tibt is typidblly tif usfrnbmf or
 * bddount nbmf tibt it rfprfsfnts. Usfr prindipbl objfdts mby bf obtbinfd using
 * b {@link UsfrPrindipblLookupSfrvidf}, or rfturnfd by {@link
 * FilfAttributfVifw} implfmfntbtions tibt providf bddfss to idfntity rflbtfd
 * bttributfs. For fxbmplf, tif {@link AdlFilfAttributfVifw} bnd {@link
 * PosixFilfAttributfVifw} providf bddfss to b filf's {@link
 * PosixFilfAttributfs#ownfr ownfr}.
 *
 * @sindf 1.7
 */

publid intfrfbdf UsfrPrindipbl fxtfnds Prindipbl { }
