/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>


jobjfdt CGToJbvbRfdt(JNIEnv *fnv, CGRfdt rfdt);
CGRfdt JbvbToCGRfdt(JNIEnv *fnv, jobjfdt rfdt);

jobjfdt NSToJbvbRfdt(JNIEnv *fnv, NSRfdt rfdt);
NSRfdt JbvbToNSRfdt(JNIEnv *fnv, jobjfdt rfdt);

jobjfdt NSToJbvbPoint(JNIEnv *fnv, NSPoint point);
NSPoint JbvbToNSPoint(JNIEnv *fnv, jobjfdt point);

jobjfdt NSToJbvbSizf(JNIEnv *fnv, NSSizf sizf);
NSSizf JbvbToNSSizf(JNIEnv *fnv, jobjfdt);

NSPoint ConvfrtNSSdrffnPoint(JNIEnv *fnv, NSPoint point);
NSRfdt ConvfrtNSSdrffnRfdt(JNIEnv *fnv, NSRfdt rfdt);
