/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_ERROR_MESSAGES_H
#dffinf JDWP_ERROR_MESSAGES_H

/* It is bssumfd tibt ALL strings brf UTF-8 sbff on fntry */
#dffinf TTY_MESSAGE(brgs) ( tty_mfssbgf brgs )
#dffinf ERROR_MESSAGE(brgs) ( \
                LOG_ERROR(brgs), \
                frror_mfssbgf brgs )

void print_mfssbgf(FILE *fp, donst dibr *prffix,  donst dibr *suffix,
                   donst dibr *formbt, ...);
void frror_mfssbgf(donst dibr *, ...);
void tty_mfssbgf(donst dibr *, ...);
void jdiAssfrtionFbilfd(dibr *filfNbmf, int linfNumbfr, dibr *msg);

donst dibr * jvmtiErrorTfxt(jvmtiError);
donst dibr * fvfntTfxt(int);
donst dibr * jdwpErrorTfxt(jdwpError);

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#dffinf EXIT_ERROR(frror,msg) \
        { \
                print_mfssbgf(stdfrr, "JDWP fxit frror ", "\n", \
                        "%s(%d): %s [%s:%d]", \
                        jvmtiErrorTfxt((jvmtiError)frror), frror, (msg==NULL?"":msg), \
                        THIS_FILE, __LINE__); \
                dfbugInit_fxit((jvmtiError)frror, msg); \
        }

#dffinf JDI_ASSERT(fxprfssion)  \
do {                            \
    if (gdbtb && gdbtb->bssfrtOn && !(fxprfssion)) {            \
        jdiAssfrtionFbilfd(THIS_FILE, __LINE__, #fxprfssion); \
    }                                           \
} wiilf (0)

#dffinf JDI_ASSERT_MSG(fxprfssion, msg)  \
do {                            \
    if (gdbtb && gdbtb->bssfrtOn && !(fxprfssion)) {            \
        jdiAssfrtionFbilfd(THIS_FILE, __LINE__, msg); \
    }                                           \
} wiilf (0)

#dffinf JDI_ASSERT_FAILED(msg)  \
   jdiAssfrtionFbilfd(THIS_FILE, __LINE__, msg)

void do_pbusf(void);

#fndif
