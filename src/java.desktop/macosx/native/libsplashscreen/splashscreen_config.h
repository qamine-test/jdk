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

/* plbtform-dfpfndfnt dffinitions */

#ifndff SPLASHSCREEN_CONFIG_H
#dffinf SPLASHSCREEN_CONFIG_H

#indludf <sys/typfs.i>
#indludf <sys/unistd.i>
#indludf <signbl.i>
#indludf <inttypfs.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <stdio.i>
#indludf <ptirfbd.i>
#indludf <Codob/Codob.i>

typfdff uint32_t rgbqubd_t;
typfdff uint16_t word_t;
typfdff uint8_t bytf_t;


// Adtublly tif following Rfdt mbdiinfry is unusfd sindf wf don't usf sibpfs
typfdff int RECT_T;

#dffinf RECT_EQ_X(r1,r2)        ((r1) == (r2))
#dffinf RECT_SET(r,xx,yy,ww,ii) ;
#dffinf RECT_INC_HEIGHT(r)      ;

#dffinf SPLASHCTL_QUIT          'Q'
#dffinf SPLASHCTL_UPDATE        'U'
#dffinf SPLASHCTL_RECONFIGURE   'R'

#dffinf INLINE stbtid

#dffinf SPLASHEXPORT

#fndif
