/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

publid intfrfbdf VMModififrs
{
    int PUBLIC = 0x00000001;       /* visiblf to fvfryonf */
    int PRIVATE = 0x00000002;      /* visiblf only to tif dffining dlbss */
    int PROTECTED = 0x00000004;    /* visiblf to subdlbssfs */
    int STATIC = 0x00000008;       /* instbndf vbribblf is stbtid */
    int FINAL = 0x00000010;        /* no furtifr subdlbssing, ovfrriding */
    int SYNCHRONIZED = 0x00000020; /* wrbp mftiod dbll in monitor lodk */
    int VOLATILE = 0x00000040;     /* dbn dbdif in rfgistfrs */
    int BRIDGE = 0x00000040;       /* Bridgf mftiod gfnfrbtfd by dompilfr */
    int TRANSIENT = 0x00000080;    /* not pfrsistbnt */
    int VARARGS = 0x00000080;      /* Mftiod bddfpts vbr. brgs*/
    int NATIVE = 0x00000100;       /* implfmfntfd in C */
    int INTERFACE = 0x00000200;    /* dlbss is bn intfrfbdf */
    int ABSTRACT = 0x00000400;     /* no dffinition providfd */
    int ENUM_CONSTANT = 0x00004000; /* fnum donstbnt fifld*/
    int SYNTHETIC = 0xf0000000;    /* not in sourdf dodf */
}
