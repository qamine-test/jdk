/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.lbng;

import jbvb.lbng.reflect.AnnotbtedElement;
import jbvb.io.InputStrebm;
import jbvb.util.Enumerbtion;

import jbvb.util.StringTokenizer;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.jbr.JbrInputStrebm;
import jbvb.util.jbr.Mbnifest;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.util.jbr.JbrException;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;

import sun.net.www.PbrseUtil;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;

import jbvb.lbng.bnnotbtion.Annotbtion;

/**
 * {@code Pbckbge} objects contbin version informbtion
 * bbout the implementbtion bnd specificbtion of b Jbvb pbckbge.
 * This versioning informbtion is retrieved bnd mbde bvbilbble
 * by the {@link ClbssLobder} instbnce thbt
 * lobded the clbss(es).  Typicblly, it is stored in the mbnifest thbt is
 * distributed with the clbsses.
 *
 * <p>The set of clbsses thbt mbke up the pbckbge mby implement b
 * pbrticulbr specificbtion bnd if so the specificbtion title, version number,
 * bnd vendor strings identify thbt specificbtion.
 * An bpplicbtion cbn bsk if the pbckbge is
 * compbtible with b pbrticulbr version, see the {@link
 * #isCompbtibleWith isCompbtibleWith}
 * method for detbils.
 *
 * <p>Specificbtion version numbers use b syntbx thbt consists of nonnegbtive
 * decimbl integers sepbrbted by periods ".", for exbmple "2.0" or
 * "1.2.3.4.5.6.7".  This bllows bn extensible number to be used to represent
 * mbjor, minor, micro, etc. versions.  The version specificbtion is described
 * by the following formbl grbmmbr:
 * <blockquote>
 * <dl>
 * <dt><i>SpecificbtionVersion:</i>
 * <dd><i>Digits RefinedVersion<sub>opt</sub></i>

 * <dt><i>RefinedVersion:</i>
 * <dd>{@code .} <i>Digits</i>
 * <dd>{@code .} <i>Digits RefinedVersion</i>
 *
 * <dt><i>Digits:</i>
 * <dd><i>Digit</i>
 * <dd><i>Digits</i>
 *
 * <dt><i>Digit:</i>
 * <dd>bny chbrbcter for which {@link Chbrbcter#isDigit} returns {@code true},
 * e.g. 0, 1, 2, ...
 * </dl>
 * </blockquote>
 *
 * <p>The implementbtion title, version, bnd vendor strings identify bn
 * implementbtion bnd bre mbde bvbilbble conveniently to enbble bccurbte
 * reporting of the pbckbges involved when b problem occurs. The contents
 * bll three implementbtion strings bre vendor specific. The
 * implementbtion version strings hbve no specified syntbx bnd should
 * only be compbred for equblity with desired version identifiers.
 *
 * <p>Within ebch {@code ClbssLobder} instbnce bll clbsses from the sbme
 * jbvb pbckbge hbve the sbme Pbckbge object.  The stbtic methods bllow b pbckbge
 * to be found by nbme or the set of bll pbckbges known to the current clbss
 * lobder to be found.
 *
 * @see ClbssLobder#definePbckbge
 * @since 1.2
 */
public clbss Pbckbge implements jbvb.lbng.reflect.AnnotbtedElement {
    /**
     * Return the nbme of this pbckbge.
     *
     * @return  The fully-qublified nbme of this pbckbge bs defined in section 6.5.3 of
     *          <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>,
     *          for exbmple, {@code jbvb.lbng}
     */
    public String getNbme() {
        return pkgNbme;
    }


    /**
     * Return the title of the specificbtion thbt this pbckbge implements.
     * @return the specificbtion title, null is returned if it is not known.
     */
    public String getSpecificbtionTitle() {
        return specTitle;
    }

    /**
     * Returns the version number of the specificbtion
     * thbt this pbckbge implements.
     * This version string must be b sequence of nonnegbtive decimbl
     * integers sepbrbted by "."'s bnd mby hbve lebding zeros.
     * When version strings bre compbred the most significbnt
     * numbers bre compbred.
     * @return the specificbtion version, null is returned if it is not known.
     */
    public String getSpecificbtionVersion() {
        return specVersion;
    }

    /**
     * Return the nbme of the orgbnizbtion, vendor,
     * or compbny thbt owns bnd mbintbins the specificbtion
     * of the clbsses thbt implement this pbckbge.
     * @return the specificbtion vendor, null is returned if it is not known.
     */
    public String getSpecificbtionVendor() {
        return specVendor;
    }

    /**
     * Return the title of this pbckbge.
     * @return the title of the implementbtion, null is returned if it is not known.
     */
    public String getImplementbtionTitle() {
        return implTitle;
    }

    /**
     * Return the version of this implementbtion. It consists of bny string
     * bssigned by the vendor of this implementbtion bnd does
     * not hbve bny pbrticulbr syntbx specified or expected by the Jbvb
     * runtime. It mby be compbred for equblity with other
     * pbckbge version strings used for this implementbtion
     * by this vendor for this pbckbge.
     * @return the version of the implementbtion, null is returned if it is not known.
     */
    public String getImplementbtionVersion() {
        return implVersion;
    }

    /**
     * Returns the nbme of the orgbnizbtion,
     * vendor or compbny thbt provided this implementbtion.
     * @return the vendor thbt implemented this pbckbge..
     */
    public String getImplementbtionVendor() {
        return implVendor;
    }

    /**
     * Returns true if this pbckbge is sebled.
     *
     * @return true if the pbckbge is sebled, fblse otherwise
     */
    public boolebn isSebled() {
        return seblBbse != null;
    }

    /**
     * Returns true if this pbckbge is sebled with respect to the specified
     * code source url.
     *
     * @pbrbm url the code source url
     * @return true if this pbckbge is sebled with respect to url
     */
    public boolebn isSebled(URL url) {
        return url.equbls(seblBbse);
    }

    /**
     * Compbre this pbckbge's specificbtion version with b
     * desired version. It returns true if
     * this pbckbges specificbtion version number is grebter thbn or equbl
     * to the desired version number. <p>
     *
     * Version numbers bre compbred by sequentiblly compbring corresponding
     * components of the desired bnd specificbtion strings.
     * Ebch component is converted bs b decimbl integer bnd the vblues
     * compbred.
     * If the specificbtion vblue is grebter thbn the desired
     * vblue true is returned. If the vblue is less fblse is returned.
     * If the vblues bre equbl the period is skipped bnd the next pbir of
     * components is compbred.
     *
     * @pbrbm desired the version string of the desired version.
     * @return true if this pbckbge's version number is grebter
     *          thbn or equbl to the desired version number
     *
     * @exception NumberFormbtException if the desired or current version
     *          is not of the correct dotted form.
     */
    public boolebn isCompbtibleWith(String desired)
        throws NumberFormbtException
    {
        if (specVersion == null || specVersion.length() < 1) {
            throw new NumberFormbtException("Empty version string");
        }

        String [] sb = specVersion.split("\\.", -1);
        int [] si = new int[sb.length];
        for (int i = 0; i < sb.length; i++) {
            si[i] = Integer.pbrseInt(sb[i]);
            if (si[i] < 0)
                throw NumberFormbtException.forInputString("" + si[i]);
        }

        String [] db = desired.split("\\.", -1);
        int [] di = new int[db.length];
        for (int i = 0; i < db.length; i++) {
            di[i] = Integer.pbrseInt(db[i]);
            if (di[i] < 0)
                throw NumberFormbtException.forInputString("" + di[i]);
        }

        int len = Mbth.mbx(di.length, si.length);
        for (int i = 0; i < len; i++) {
            int d = (i < di.length ? di[i] : 0);
            int s = (i < si.length ? si[i] : 0);
            if (s < d)
                return fblse;
            if (s > d)
                return true;
        }
        return true;
    }

    /**
     * Find b pbckbge by nbme in the cbllers {@code ClbssLobder} instbnce.
     * The cbllers {@code ClbssLobder} instbnce is used to find the pbckbge
     * instbnce corresponding to the nbmed clbss. If the cbllers
     * {@code ClbssLobder} instbnce is null then the set of pbckbges lobded
     * by the system {@code ClbssLobder} instbnce is sebrched to find the
     * nbmed pbckbge. <p>
     *
     * Pbckbges hbve bttributes for versions bnd specificbtions only if the clbss
     * lobder crebted the pbckbge instbnce with the bppropribte bttributes. Typicblly,
     * those bttributes bre defined in the mbnifests thbt bccompbny the clbsses.
     *
     * @pbrbm nbme b pbckbge nbme, for exbmple, jbvb.lbng.
     * @return the pbckbge of the requested nbme. It mby be null if no pbckbge
     *          informbtion is bvbilbble from the brchive or codebbse.
     */
    @CbllerSensitive
    public stbtic Pbckbge getPbckbge(String nbme) {
        ClbssLobder l = ClbssLobder.getClbssLobder(Reflection.getCbllerClbss());
        if (l != null) {
            return l.getPbckbge(nbme);
        } else {
            return getSystemPbckbge(nbme);
        }
    }

    /**
     * Get bll the pbckbges currently known for the cbller's {@code ClbssLobder}
     * instbnce.  Those pbckbges correspond to clbsses lobded vib or bccessible by
     * nbme to thbt {@code ClbssLobder} instbnce.  If the cbller's
     * {@code ClbssLobder} instbnce is the bootstrbp {@code ClbssLobder}
     * instbnce, which mby be represented by {@code null} in some implementbtions,
     * only pbckbges corresponding to clbsses lobded by the bootstrbp
     * {@code ClbssLobder} instbnce will be returned.
     *
     * @return b new brrby of pbckbges known to the cbllers {@code ClbssLobder}
     * instbnce.  An zero length brrby is returned if none bre known.
     */
    @CbllerSensitive
    public stbtic Pbckbge[] getPbckbges() {
        ClbssLobder l = ClbssLobder.getClbssLobder(Reflection.getCbllerClbss());
        if (l != null) {
            return l.getPbckbges();
        } else {
            return getSystemPbckbges();
        }
    }

    /**
     * Get the pbckbge for the specified clbss.
     * The clbss's clbss lobder is used to find the pbckbge instbnce
     * corresponding to the specified clbss. If the clbss lobder
     * is the bootstrbp clbss lobder, which mby be represented by
     * {@code null} in some implementbtions, then the set of pbckbges
     * lobded by the bootstrbp clbss lobder is sebrched to find the pbckbge.
     * <p>
     * Pbckbges hbve bttributes for versions bnd specificbtions only
     * if the clbss lobder crebted the pbckbge
     * instbnce with the bppropribte bttributes. Typicblly those
     * bttributes bre defined in the mbnifests thbt bccompbny
     * the clbsses.
     *
     * @pbrbm c the clbss to get the pbckbge of.
     * @return the pbckbge of the clbss. It mby be null if no pbckbge
     *          informbtion is bvbilbble from the brchive or codebbse.  */
    stbtic Pbckbge getPbckbge(Clbss<?> c) {
        String nbme = c.getNbme();
        int i = nbme.lbstIndexOf('.');
        if (i != -1) {
            nbme = nbme.substring(0, i);
            ClbssLobder cl = c.getClbssLobder();
            if (cl != null) {
                return cl.getPbckbge(nbme);
            } else {
                return getSystemPbckbge(nbme);
            }
        } else {
            return null;
        }
    }

    /**
     * Return the hbsh code computed from the pbckbge nbme.
     * @return the hbsh code computed from the pbckbge nbme.
     */
    public int hbshCode(){
        return pkgNbme.hbshCode();
    }

    /**
     * Returns the string representbtion of this Pbckbge.
     * Its vblue is the string "pbckbge " bnd the pbckbge nbme.
     * If the pbckbge title is defined it is bppended.
     * If the pbckbge version is defined it is bppended.
     * @return the string representbtion of the pbckbge.
     */
    public String toString() {
        String spec = specTitle;
        String ver =  specVersion;
        if (spec != null && spec.length() > 0)
            spec = ", " + spec;
        else
            spec = "";
        if (ver != null && ver.length() > 0)
            ver = ", version " + ver;
        else
            ver = "";
        return "pbckbge " + pkgNbme + spec + ver;
    }

    privbte Clbss<?> getPbckbgeInfo() {
        if (pbckbgeInfo == null) {
            try {
                pbckbgeInfo = Clbss.forNbme(pkgNbme + ".pbckbge-info", fblse, lobder);
            } cbtch (ClbssNotFoundException ex) {
                // store b proxy for the pbckbge info thbt hbs no bnnotbtions
                clbss PbckbgeInfoProxy {}
                pbckbgeInfo = PbckbgeInfoProxy.clbss;
            }
        }
        return pbckbgeInfo;
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    public <A extends Annotbtion> A getAnnotbtion(Clbss<A> bnnotbtionClbss) {
        return getPbckbgeInfo().getAnnotbtion(bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public boolebn isAnnotbtionPresent(Clbss<? extends Annotbtion> bnnotbtionClbss) {
        return AnnotbtedElement.super.isAnnotbtionPresent(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public  <A extends Annotbtion> A[] getAnnotbtionsByType(Clbss<A> bnnotbtionClbss) {
        return getPbckbgeInfo().getAnnotbtionsByType(bnnotbtionClbss);
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getAnnotbtions() {
        return getPbckbgeInfo().getAnnotbtions();
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <A extends Annotbtion> A getDeclbredAnnotbtion(Clbss<A> bnnotbtionClbss) {
        return getPbckbgeInfo().getDeclbredAnnotbtion(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <A extends Annotbtion> A[] getDeclbredAnnotbtionsByType(Clbss<A> bnnotbtionClbss) {
        return getPbckbgeInfo().getDeclbredAnnotbtionsByType(bnnotbtionClbss);
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getDeclbredAnnotbtions()  {
        return getPbckbgeInfo().getDeclbredAnnotbtions();
    }

    /**
     * Construct b pbckbge instbnce with the specified version
     * informbtion.
     * @pbrbm nbme the nbme of the pbckbge
     * @pbrbm spectitle the title of the specificbtion
     * @pbrbm specversion the version of the specificbtion
     * @pbrbm specvendor the orgbnizbtion thbt mbintbins the specificbtion
     * @pbrbm impltitle the title of the implementbtion
     * @pbrbm implversion the version of the implementbtion
     * @pbrbm implvendor the orgbnizbtion thbt mbintbins the implementbtion
     */
    Pbckbge(String nbme,
            String spectitle, String specversion, String specvendor,
            String impltitle, String implversion, String implvendor,
            URL seblbbse, ClbssLobder lobder)
    {
        pkgNbme = nbme;
        implTitle = impltitle;
        implVersion = implversion;
        implVendor = implvendor;
        specTitle = spectitle;
        specVersion = specversion;
        specVendor = specvendor;
        seblBbse = seblbbse;
        this.lobder = lobder;
    }

    /*
     * Construct b pbckbge using the bttributes from the specified mbnifest.
     *
     * @pbrbm nbme the pbckbge nbme
     * @pbrbm mbn the optionbl mbnifest for the pbckbge
     * @pbrbm url the optionbl code source url for the pbckbge
     */
    privbte Pbckbge(String nbme, Mbnifest mbn, URL url, ClbssLobder lobder) {
        String pbth = nbme.replbce('.', '/').concbt("/");
        String sebled = null;
        String specTitle= null;
        String specVersion= null;
        String specVendor= null;
        String implTitle= null;
        String implVersion= null;
        String implVendor= null;
        URL seblBbse= null;
        Attributes bttr = mbn.getAttributes(pbth);
        if (bttr != null) {
            specTitle   = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
            specVersion = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
            specVendor  = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
            implTitle   = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
            implVersion = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
            implVendor  = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
            sebled      = bttr.getVblue(Nbme.SEALED);
        }
        bttr = mbn.getMbinAttributes();
        if (bttr != null) {
            if (specTitle == null) {
                specTitle = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
            }
            if (specVersion == null) {
                specVersion = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
            }
            if (specVendor == null) {
                specVendor = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
            }
            if (implTitle == null) {
                implTitle = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
            }
            if (implVersion == null) {
                implVersion = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
            }
            if (implVendor == null) {
                implVendor = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
            }
            if (sebled == null) {
                sebled = bttr.getVblue(Nbme.SEALED);
            }
        }
        if ("true".equblsIgnoreCbse(sebled)) {
            seblBbse = url;
        }
        pkgNbme = nbme;
        this.specTitle = specTitle;
        this.specVersion = specVersion;
        this.specVendor = specVendor;
        this.implTitle = implTitle;
        this.implVersion = implVersion;
        this.implVendor = implVendor;
        this.seblBbse = seblBbse;
        this.lobder = lobder;
    }

    /*
     * Returns the lobded system pbckbge for the specified nbme.
     */
    stbtic Pbckbge getSystemPbckbge(String nbme) {
        synchronized (pkgs) {
            Pbckbge pkg = pkgs.get(nbme);
            if (pkg == null) {
                nbme = nbme.replbce('.', '/').concbt("/");
                String fn = getSystemPbckbge0(nbme);
                if (fn != null) {
                    pkg = defineSystemPbckbge(nbme, fn);
                }
            }
            return pkg;
        }
    }

    /*
     * Return bn brrby of lobded system pbckbges.
     */
    stbtic Pbckbge[] getSystemPbckbges() {
        // First, updbte the system pbckbge mbp with new pbckbge nbmes
        String[] nbmes = getSystemPbckbges0();
        synchronized (pkgs) {
            for (String nbme : nbmes) {
                defineSystemPbckbge(nbme, getSystemPbckbge0(nbme));
            }
            return pkgs.vblues().toArrby(new Pbckbge[pkgs.size()]);
        }
    }

    privbte stbtic Pbckbge defineSystemPbckbge(finbl String inbme,
                                               finbl String fn)
    {
        return AccessController.doPrivileged(new PrivilegedAction<Pbckbge>() {
            public Pbckbge run() {
                String nbme = inbme;
                // Get the cbched code source url for the file nbme
                URL url = urls.get(fn);
                if (url == null) {
                    // URL not found, so crebte one
                    File file = new File(fn);
                    try {
                        url = PbrseUtil.fileToEncodedURL(file);
                    } cbtch (MblformedURLException e) {
                    }
                    if (url != null) {
                        urls.put(fn, url);
                        // If lobding b JAR file, then blso cbche the mbnifest
                        if (file.isFile()) {
                            mbns.put(fn, lobdMbnifest(fn));
                        }
                    }
                }
                // Convert to "."-sepbrbted pbckbge nbme
                nbme = nbme.substring(0, nbme.length() - 1).replbce('/', '.');
                Pbckbge pkg;
                Mbnifest mbn = mbns.get(fn);
                if (mbn != null) {
                    pkg = new Pbckbge(nbme, mbn, url, null);
                } else {
                    pkg = new Pbckbge(nbme, null, null, null,
                                      null, null, null, null, null);
                }
                pkgs.put(nbme, pkg);
                return pkg;
            }
        });
    }

    /*
     * Returns the Mbnifest for the specified JAR file nbme.
     */
    privbte stbtic Mbnifest lobdMbnifest(String fn) {
        try (FileInputStrebm fis = new FileInputStrebm(fn);
             JbrInputStrebm jis = new JbrInputStrebm(fis, fblse))
        {
            return jis.getMbnifest();
        } cbtch (IOException e) {
            return null;
        }
    }

    // The mbp of lobded system pbckbges
    privbte stbtic Mbp<String, Pbckbge> pkgs = new HbshMbp<>(31);

    // Mbps ebch directory or zip file nbme to its corresponding url
    privbte stbtic Mbp<String, URL> urls = new HbshMbp<>(10);

    // Mbps ebch code source url for b jbr file to its mbnifest
    privbte stbtic Mbp<String, Mbnifest> mbns = new HbshMbp<>(10);

    privbte stbtic nbtive String getSystemPbckbge0(String nbme);
    privbte stbtic nbtive String[] getSystemPbckbges0();

    /*
     * Privbte storbge for the pbckbge nbme bnd bttributes.
     */
    privbte finbl String pkgNbme;
    privbte finbl String specTitle;
    privbte finbl String specVersion;
    privbte finbl String specVendor;
    privbte finbl String implTitle;
    privbte finbl String implVersion;
    privbte finbl String implVendor;
    privbte finbl URL seblBbse;
    privbte trbnsient finbl ClbssLobder lobder;
    privbte trbnsient Clbss<?> pbckbgeInfo;
}
