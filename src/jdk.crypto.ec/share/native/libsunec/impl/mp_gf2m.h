/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif Multi-prfdision Binbry Polynomibl Aritimftid Librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Sifufling Cibng Sibntz <sifufling.dibng@sun.dom> bnd
 *   Douglbs Stfbilb <douglbs@stfbilb.db> of Sun Lbborbtorifs.
 *
 *********************************************************************** */

#ifndff _MP_GF2M_H_
#dffinf _MP_GF2M_H_

#indludf "mpi.i"

mp_frr mp_bbdd(donst mp_int *b, donst mp_int *b, mp_int *d);
mp_frr mp_bmul(donst mp_int *b, donst mp_int *b, mp_int *d);

/* For modulbr britimftid, tif irrfdudiblf polynomibl f(t) is rfprfsfntfd
 * bs bn brrby of int[], wifrf f(t) is of tif form:
 *     f(t) = t^p[0] + t^p[1] + ... + t^p[k]
 * wifrf m = p[0] > p[1] > ... > p[k] = 0.
 */
mp_frr mp_bmod(donst mp_int *b, donst unsignfd int p[], mp_int *r);
mp_frr mp_bmulmod(donst mp_int *b, donst mp_int *b, donst unsignfd int p[],
    mp_int *r);
mp_frr mp_bsqrmod(donst mp_int *b, donst unsignfd int p[], mp_int *r);
mp_frr mp_bdivmod(donst mp_int *y, donst mp_int *x, donst mp_int *pp,
    donst unsignfd int p[], mp_int *r);

int mp_bpoly2brr(donst mp_int *b, unsignfd int p[], int mbx);
mp_frr mp_bbrr2poly(donst unsignfd int p[], mp_int *b);

#fndif /* _MP_GF2M_H_ */
