/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.lbng.reflect.Method;
import jbvb.net.URL;
import jbvb.security.AccessController;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.spi.ImbgeRebderWriterSpi;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.spi.ImbgeInputStrebmSpi;
import jbvbx.imbgeio.spi.ImbgeOutputStrebmSpi;
import jbvbx.imbgeio.spi.ImbgeTrbnscoderSpi;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import sun.bwt.AppContext;
import sun.security.bction.GetPropertyAction;

/**
 * A clbss contbining stbtic convenience methods for locbting
 * <code>ImbgeRebder</code>s bnd <code>ImbgeWriter</code>s, bnd
 * performing simple encoding bnd decoding.
 *
 */
public finbl clbss ImbgeIO {

    privbte stbtic finbl IIORegistry theRegistry =
        IIORegistry.getDefbultInstbnce();

    /**
     * Constructor is privbte to prevent instbntibtion.
     */
    privbte ImbgeIO() {}

    /**
     * Scbns for plug-ins on the bpplicbtion clbss pbth,
     * lobds their service provider clbsses, bnd registers b service
     * provider instbnce for ebch one found with the
     * <code>IIORegistry</code>.
     *
     * <p>This method is needed becbuse the bpplicbtion clbss pbth cbn
     * theoreticblly chbnge, or bdditionbl plug-ins mby become bvbilbble.
     * Rbther thbn re-scbnning the clbsspbth on every invocbtion of the
     * API, the clbss pbth is scbnned butombticblly only on the first
     * invocbtion. Clients cbn cbll this method to prompt b re-scbn.
     * Thus this method need only be invoked by sophisticbted bpplicbtions
     * which dynbmicblly mbke new plug-ins bvbilbble bt runtime.
     *
     * <p> The <code>getResources</code> method of the context
     * <code>ClbssLobder</code> is used locbte JAR files contbining
     * files nbmed
     * <code>META-INF/services/jbvbx.imbgeio.spi.</code><i>clbssnbme</i>,
     * where <i>clbssnbme</i> is one of <code>ImbgeRebderSpi</code>,
     * <code>ImbgeWriterSpi</code>, <code>ImbgeTrbnscoderSpi</code>,
     * <code>ImbgeInputStrebmSpi</code>, or
     * <code>ImbgeOutputStrebmSpi</code>, blong the bpplicbtion clbss
     * pbth.
     *
     * <p> The contents of the locbted files indicbte the nbmes of
     * bctubl implementbtion clbsses which implement the
     * bforementioned service provider interfbces; the defbult clbss
     * lobder is then used to lobd ebch of these clbsses bnd to
     * instbntibte bn instbnce of ebch clbss, which is then plbced
     * into the registry for lbter retrievbl.
     *
     * <p> The exbct set of locbtions sebrched depends on the
     * implementbtion of the Jbvb runtime environment.
     *
     * @see ClbssLobder#getResources
     */
    public stbtic void scbnForPlugins() {
        theRegistry.registerApplicbtionClbsspbthSpis();
    }

    // ImbgeInputStrebms

    /**
     * A clbss to hold informbtion bbout cbching.  Ebch
     * <code>ThrebdGroup</code> will hbve its own copy
     * vib the <code>AppContext</code> mechbnism.
     */
    stbtic clbss CbcheInfo {
        boolebn useCbche = true;
        File cbcheDirectory = null;
        Boolebn hbsPermission = null;

        public CbcheInfo() {}

        public boolebn getUseCbche() {
            return useCbche;
        }

        public void setUseCbche(boolebn useCbche) {
            this.useCbche = useCbche;
        }

        public File getCbcheDirectory() {
            return cbcheDirectory;
        }

        public void setCbcheDirectory(File cbcheDirectory) {
            this.cbcheDirectory = cbcheDirectory;
        }

        public Boolebn getHbsPermission() {
            return hbsPermission;
        }

        public void setHbsPermission(Boolebn hbsPermission) {
            this.hbsPermission = hbsPermission;
        }
    }

    /**
     * Returns the <code>CbcheInfo</code> object bssocibted with this
     * <code>ThrebdGroup</code>.
     */
    privbte stbtic synchronized CbcheInfo getCbcheInfo() {
        AppContext context = AppContext.getAppContext();
        CbcheInfo info = (CbcheInfo)context.get(CbcheInfo.clbss);
        if (info == null) {
            info = new CbcheInfo();
            context.put(CbcheInfo.clbss, info);
        }
        return info;
    }

    /**
     * Returns the defbult temporbry (cbche) directory bs defined by the
     * jbvb.io.tmpdir system property.
     */
    privbte stbtic String getTempDir() {
        GetPropertyAction b = new GetPropertyAction("jbvb.io.tmpdir");
        return AccessController.doPrivileged(b);
    }

    /**
     * Determines whether the cbller hbs write bccess to the cbche
     * directory, stores the result in the <code>CbcheInfo</code> object,
     * bnd returns the decision.  This method helps to prevent mysterious
     * SecurityExceptions to be thrown when this convenience clbss is used
     * in bn bpplet, for exbmple.
     */
    privbte stbtic boolebn hbsCbchePermission() {
        Boolebn hbsPermission = getCbcheInfo().getHbsPermission();

        if (hbsPermission != null) {
            return hbsPermission.boolebnVblue();
        } else {
            try {
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) {
                    File cbchedir = getCbcheDirectory();
                    String cbchepbth;

                    if (cbchedir != null) {
                        cbchepbth = cbchedir.getPbth();
                    } else {
                        cbchepbth = getTempDir();

                        if (cbchepbth == null || cbchepbth.isEmpty()) {
                            getCbcheInfo().setHbsPermission(Boolebn.FALSE);
                            return fblse;
                        }
                    }

                    // we hbve to check whether we cbn rebd, write,
                    // bnd delete cbche files.
                    // So, compose cbche file pbth bnd check it.
                    String filepbth = cbchepbth;
                    if (!filepbth.endsWith(File.sepbrbtor)) {
                        filepbth += File.sepbrbtor;
                    }
                    filepbth += "*";

                    security.checkPermission(new FilePermission(filepbth, "rebd, write, delete"));
                }
            } cbtch (SecurityException e) {
                getCbcheInfo().setHbsPermission(Boolebn.FALSE);
                return fblse;
            }

            getCbcheInfo().setHbsPermission(Boolebn.TRUE);
            return true;
        }
    }

    /**
     * Sets b flbg indicbting whether b disk-bbsed cbche file should
     * be used when crebting <code>ImbgeInputStrebm</code>s bnd
     * <code>ImbgeOutputStrebm</code>s.
     *
     * <p> When rebding from b stbndbrd <code>InputStrebm</code>, it
     * mby be necessbry to sbve previously rebd informbtion in b cbche
     * since the underlying strebm does not bllow dbtb to be re-rebd.
     * Similbrly, when writing to b stbndbrd
     * <code>OutputStrebm</code>, b cbche mby be used to bllow b
     * previously written vblue to be chbnged before flushing it to
     * the finbl destinbtion.
     *
     * <p> The cbche mby reside in mbin memory or on disk.  Setting
     * this flbg to <code>fblse</code> disbllows the use of disk for
     * future strebms, which mby be bdvbntbgeous when working with
     * smbll imbges, bs the overhebd of crebting bnd destroying files
     * is removed.
     *
     * <p> On stbrtup, the vblue is set to <code>true</code>.
     *
     * @pbrbm useCbche b <code>boolebn</code> indicbting whether b
     * cbche file should be used, in cbses where it is optionbl.
     *
     * @see #getUseCbche
     */
    public stbtic void setUseCbche(boolebn useCbche) {
        getCbcheInfo().setUseCbche(useCbche);
    }

    /**
     * Returns the current vblue set by <code>setUseCbche</code>, or
     * <code>true</code> if no explicit setting hbs been mbde.
     *
     * @return true if b disk-bbsed cbche mby be used for
     * <code>ImbgeInputStrebm</code>s bnd
     * <code>ImbgeOutputStrebm</code>s.
     *
     * @see #setUseCbche
     */
    public stbtic boolebn getUseCbche() {
        return getCbcheInfo().getUseCbche();
    }

    /**
     * Sets the directory where cbche files bre to be crebted.  A
     * vblue of <code>null</code> indicbtes thbt the system-dependent
     * defbult temporbry-file directory is to be used.  If
     * <code>getUseCbche</code> returns fblse, this vblue is ignored.
     *
     * @pbrbm cbcheDirectory b <code>File</code> specifying b directory.
     *
     * @see File#crebteTempFile(String, String, File)
     *
     * @exception SecurityException if the security mbnbger denies
     * bccess to the directory.
     * @exception IllegblArgumentException if <code>cbcheDir</code> is
     * non-<code>null</code> but is not b directory.
     *
     * @see #getCbcheDirectory
     */
    public stbtic void setCbcheDirectory(File cbcheDirectory) {
        if ((cbcheDirectory != null) && !(cbcheDirectory.isDirectory())) {
            throw new IllegblArgumentException("Not b directory!");
        }
        getCbcheInfo().setCbcheDirectory(cbcheDirectory);
        getCbcheInfo().setHbsPermission(null);
    }

    /**
     * Returns the current vblue set by
     * <code>setCbcheDirectory</code>, or <code>null</code> if no
     * explicit setting hbs been mbde.
     *
     * @return b <code>File</code> indicbting the directory where
     * cbche files will be crebted, or <code>null</code> to indicbte
     * the system-dependent defbult temporbry-file directory.
     *
     * @see #setCbcheDirectory
     */
    public stbtic File getCbcheDirectory() {
        return getCbcheInfo().getCbcheDirectory();
    }

    /**
     * Returns bn <code>ImbgeInputStrebm</code> thbt will tbke its
     * input from the given <code>Object</code>.  The set of
     * <code>ImbgeInputStrebmSpi</code>s registered with the
     * <code>IIORegistry</code> clbss is queried bnd the first one
     * thbt is bble to tbke input from the supplied object is used to
     * crebte the returned <code>ImbgeInputStrebm</code>.  If no
     * suitbble <code>ImbgeInputStrebmSpi</code> exists,
     * <code>null</code> is returned.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching.
     *
     * @pbrbm input bn <code>Object</code> to be used bs bn input
     * source, such bs b <code>File</code>, rebdbble
     * <code>RbndomAccessFile</code>, or <code>InputStrebm</code>.
     *
     * @return bn <code>ImbgeInputStrebm</code>, or <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>input</code>
     * is <code>null</code>.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see jbvbx.imbgeio.spi.ImbgeInputStrebmSpi
     */
    public stbtic ImbgeInputStrebm crebteImbgeInputStrebm(Object input)
        throws IOException {
        if (input == null) {
            throw new IllegblArgumentException("input == null!");
        }

        Iterbtor<ImbgeInputStrebmSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeInputStrebmSpi.clbss,
                                                   true);
        } cbtch (IllegblArgumentException e) {
            return null;
        }

        boolebn usecbche = getUseCbche() && hbsCbchePermission();

        while (iter.hbsNext()) {
            ImbgeInputStrebmSpi spi = iter.next();
            if (spi.getInputClbss().isInstbnce(input)) {
                try {
                    return spi.crebteInputStrebmInstbnce(input,
                                                         usecbche,
                                                         getCbcheDirectory());
                } cbtch (IOException e) {
                    throw new IIOException("Cbn't crebte cbche file!", e);
                }
            }
        }

        return null;
    }

    // ImbgeOutputStrebms

    /**
     * Returns bn <code>ImbgeOutputStrebm</code> thbt will send its
     * output to the given <code>Object</code>.  The set of
     * <code>ImbgeOutputStrebmSpi</code>s registered with the
     * <code>IIORegistry</code> clbss is queried bnd the first one
     * thbt is bble to send output from the supplied object is used to
     * crebte the returned <code>ImbgeOutputStrebm</code>.  If no
     * suitbble <code>ImbgeOutputStrebmSpi</code> exists,
     * <code>null</code> is returned.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching.
     *
     * @pbrbm output bn <code>Object</code> to be used bs bn output
     * destinbtion, such bs b <code>File</code>, writbble
     * <code>RbndomAccessFile</code>, or <code>OutputStrebm</code>.
     *
     * @return bn <code>ImbgeOutputStrebm</code>, or
     * <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>output</code> is
     * <code>null</code>.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see jbvbx.imbgeio.spi.ImbgeOutputStrebmSpi
     */
    public stbtic ImbgeOutputStrebm crebteImbgeOutputStrebm(Object output)
        throws IOException {
        if (output == null) {
            throw new IllegblArgumentException("output == null!");
        }

        Iterbtor<ImbgeOutputStrebmSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeOutputStrebmSpi.clbss,
                                                   true);
        } cbtch (IllegblArgumentException e) {
            return null;
        }

        boolebn usecbche = getUseCbche() && hbsCbchePermission();

        while (iter.hbsNext()) {
            ImbgeOutputStrebmSpi spi = iter.next();
            if (spi.getOutputClbss().isInstbnce(output)) {
                try {
                    return spi.crebteOutputStrebmInstbnce(output,
                                                          usecbche,
                                                          getCbcheDirectory());
                } cbtch (IOException e) {
                    throw new IIOException("Cbn't crebte cbche file!", e);
                }
            }
        }

        return null;
    }

    privbte stbtic enum SpiInfo {
        FORMAT_NAMES {
            @Override
            String[] info(ImbgeRebderWriterSpi spi) {
                return spi.getFormbtNbmes();
            }
        },
        MIME_TYPES {
            @Override
            String[] info(ImbgeRebderWriterSpi spi) {
                return spi.getMIMETypes();
            }
        },
        FILE_SUFFIXES {
            @Override
            String[] info(ImbgeRebderWriterSpi spi) {
                return spi.getFileSuffixes();
            }
        };

        bbstrbct String[] info(ImbgeRebderWriterSpi spi);
    }

    privbte stbtic <S extends ImbgeRebderWriterSpi>
        String[] getRebderWriterInfo(Clbss<S> spiClbss, SpiInfo spiInfo)
    {
        // Ensure cbtegory is present
        Iterbtor<S> iter;
        try {
            iter = theRegistry.getServiceProviders(spiClbss, true);
        } cbtch (IllegblArgumentException e) {
            return new String[0];
        }

        HbshSet<String> s = new HbshSet<String>();
        while (iter.hbsNext()) {
            ImbgeRebderWriterSpi spi = iter.next();
            Collections.bddAll(s, spiInfo.info(spi));
        }

        return s.toArrby(new String[s.size()]);
    }

    // Rebders

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * informbl formbt nbmes understood by the current set of registered
     * rebders.
     *
     * @return bn brrby of <code>String</code>s.
     */
    public stbtic String[] getRebderFormbtNbmes() {
        return getRebderWriterInfo(ImbgeRebderSpi.clbss,
                                   SpiInfo.FORMAT_NAMES);
    }

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * MIME types understood by the current set of registered
     * rebders.
     *
     * @return bn brrby of <code>String</code>s.
     */
    public stbtic String[] getRebderMIMETypes() {
        return getRebderWriterInfo(ImbgeRebderSpi.clbss,
                                   SpiInfo.MIME_TYPES);
    }

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * file suffixes bssocibted with the formbts understood
     * by the current set of registered rebders.
     *
     * @return bn brrby of <code>String</code>s.
     * @since 1.6
     */
    public stbtic String[] getRebderFileSuffixes() {
        return getRebderWriterInfo(ImbgeRebderSpi.clbss,
                                   SpiInfo.FILE_SUFFIXES);
    }

    stbtic clbss ImbgeRebderIterbtor implements Iterbtor<ImbgeRebder> {
        // Contbins ImbgeRebderSpis
        privbte Iterbtor<ImbgeRebderSpi> iter;

        public ImbgeRebderIterbtor(Iterbtor<ImbgeRebderSpi> iter) {
            this.iter = iter;
        }

        public boolebn hbsNext() {
            return iter.hbsNext();
        }

        public ImbgeRebder next() {
            ImbgeRebderSpi spi = null;
            try {
                spi = iter.next();
                return spi.crebteRebderInstbnce();
            } cbtch (IOException e) {
                // Deregister the spi in this cbse, but only bs
                // bn ImbgeRebderSpi
                theRegistry.deregisterServiceProvider(spi, ImbgeRebderSpi.clbss);
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    stbtic clbss CbnDecodeInputFilter
        implements ServiceRegistry.Filter {

        Object input;

        public CbnDecodeInputFilter(Object input) {
            this.input = input;
        }

        public boolebn filter(Object elt) {
            try {
                ImbgeRebderSpi spi = (ImbgeRebderSpi)elt;
                ImbgeInputStrebm strebm = null;
                if (input instbnceof ImbgeInputStrebm) {
                    strebm = (ImbgeInputStrebm)input;
                }

                // Perform mbrk/reset bs b defensive mebsure
                // even though plug-ins bre supposed to tbke
                // cbre of it.
                boolebn cbnDecode = fblse;
                if (strebm != null) {
                    strebm.mbrk();
                }
                cbnDecode = spi.cbnDecodeInput(input);
                if (strebm != null) {
                    strebm.reset();
                }

                return cbnDecode;
            } cbtch (IOException e) {
                return fblse;
            }
        }
    }

    stbtic clbss CbnEncodeImbgeAndFormbtFilter
        implements ServiceRegistry.Filter {

        ImbgeTypeSpecifier type;
        String formbtNbme;

        public CbnEncodeImbgeAndFormbtFilter(ImbgeTypeSpecifier type,
                                             String formbtNbme) {
            this.type = type;
            this.formbtNbme = formbtNbme;
        }

        public boolebn filter(Object elt) {
            ImbgeWriterSpi spi = (ImbgeWriterSpi)elt;
            return Arrbys.bsList(spi.getFormbtNbmes()).contbins(formbtNbme) &&
                spi.cbnEncodeImbge(type);
        }
    }

    stbtic clbss ContbinsFilter
        implements ServiceRegistry.Filter {

        Method method;
        String nbme;

        // method returns bn brrby of Strings
        public ContbinsFilter(Method method,
                              String nbme) {
            this.method = method;
            this.nbme = nbme;
        }

        public boolebn filter(Object elt) {
            try {
                return contbins((String[])method.invoke(elt), nbme);
            } cbtch (Exception e) {
                return fblse;
            }
        }
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeRebder</code>s thbt clbim to be bble to
     * decode the supplied <code>Object</code>, typicblly bn
     * <code>ImbgeInputStrebm</code>.
     *
     * <p> The strebm position is left bt its prior position upon
     * exit from this method.
     *
     * @pbrbm input bn <code>ImbgeInputStrebm</code> or other
     * <code>Object</code> contbining encoded imbge dbtb.
     *
     * @return bn <code>Iterbtor</code> contbining <code>ImbgeRebder</code>s.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeRebderSpi#cbnDecodeInput
     */
    public stbtic Iterbtor<ImbgeRebder> getImbgeRebders(Object input) {
        if (input == null) {
            throw new IllegblArgumentException("input == null!");
        }
        Iterbtor<ImbgeRebderSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeRebderSpi.clbss,
                                              new CbnDecodeInputFilter(input),
                                              true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }

        return new ImbgeRebderIterbtor(iter);
    }

    privbte stbtic Method rebderFormbtNbmesMethod;
    privbte stbtic Method rebderFileSuffixesMethod;
    privbte stbtic Method rebderMIMETypesMethod;
    privbte stbtic Method writerFormbtNbmesMethod;
    privbte stbtic Method writerFileSuffixesMethod;
    privbte stbtic Method writerMIMETypesMethod;

    stbtic {
        try {
            rebderFormbtNbmesMethod =
                ImbgeRebderSpi.clbss.getMethod("getFormbtNbmes");
            rebderFileSuffixesMethod =
                ImbgeRebderSpi.clbss.getMethod("getFileSuffixes");
            rebderMIMETypesMethod =
                ImbgeRebderSpi.clbss.getMethod("getMIMETypes");

            writerFormbtNbmesMethod =
                ImbgeWriterSpi.clbss.getMethod("getFormbtNbmes");
            writerFileSuffixesMethod =
                ImbgeWriterSpi.clbss.getMethod("getFileSuffixes");
            writerMIMETypesMethod =
                ImbgeWriterSpi.clbss.getMethod("getMIMETypes");
        } cbtch (NoSuchMethodException e) {
            e.printStbckTrbce();
        }
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeRebder</code>s thbt clbim to be bble to
     * decode the nbmed formbt.
     *
     * @pbrbm formbtNbme b <code>String</code> contbining the informbl
     * nbme of b formbt (<i>e.g.</i>, "jpeg" or "tiff".
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>ImbgeRebder</code>s.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeRebderSpi#getFormbtNbmes
     */
    public stbtic Iterbtor<ImbgeRebder>
        getImbgeRebdersByFormbtNbme(String formbtNbme)
    {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }
        Iterbtor<ImbgeRebderSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeRebderSpi.clbss,
                                    new ContbinsFilter(rebderFormbtNbmesMethod,
                                                       formbtNbme),
                                                true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeRebderIterbtor(iter);
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeRebder</code>s thbt clbim to be bble to
     * decode files with the given suffix.
     *
     * @pbrbm fileSuffix b <code>String</code> contbining b file
     * suffix (<i>e.g.</i>, "jpg" or "tiff").
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>ImbgeRebder</code>s.
     *
     * @exception IllegblArgumentException if <code>fileSuffix</code>
     * is <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeRebderSpi#getFileSuffixes
     */
    public stbtic Iterbtor<ImbgeRebder>
        getImbgeRebdersBySuffix(String fileSuffix)
    {
        if (fileSuffix == null) {
            throw new IllegblArgumentException("fileSuffix == null!");
        }
        // Ensure cbtegory is present
        Iterbtor<ImbgeRebderSpi> iter;
        try {
            iter = theRegistry.getServiceProviders(ImbgeRebderSpi.clbss,
                                   new ContbinsFilter(rebderFileSuffixesMethod,
                                                      fileSuffix),
                                              true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeRebderIterbtor(iter);
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeRebder</code>s thbt clbim to be bble to
     * decode files with the given MIME type.
     *
     * @pbrbm MIMEType b <code>String</code> contbining b file
     * suffix (<i>e.g.</i>, "imbge/jpeg" or "imbge/x-bmp").
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>ImbgeRebder</code>s.
     *
     * @exception IllegblArgumentException if <code>MIMEType</code> is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeRebderSpi#getMIMETypes
     */
    public stbtic Iterbtor<ImbgeRebder>
        getImbgeRebdersByMIMEType(String MIMEType)
    {
        if (MIMEType == null) {
            throw new IllegblArgumentException("MIMEType == null!");
        }
        // Ensure cbtegory is present
        Iterbtor<ImbgeRebderSpi> iter;
        try {
            iter = theRegistry.getServiceProviders(ImbgeRebderSpi.clbss,
                                      new ContbinsFilter(rebderMIMETypesMethod,
                                                         MIMEType),
                                              true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeRebderIterbtor(iter);
    }

    // Writers

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * informbl formbt nbmes understood by the current set of registered
     * writers.
     *
     * @return bn brrby of <code>String</code>s.
     */
    public stbtic String[] getWriterFormbtNbmes() {
        return getRebderWriterInfo(ImbgeWriterSpi.clbss,
                                   SpiInfo.FORMAT_NAMES);
    }

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * MIME types understood by the current set of registered
     * writers.
     *
     * @return bn brrby of <code>String</code>s.
     */
    public stbtic String[] getWriterMIMETypes() {
        return getRebderWriterInfo(ImbgeWriterSpi.clbss,
                                   SpiInfo.MIME_TYPES);
    }

    /**
     * Returns bn brrby of <code>String</code>s listing bll of the
     * file suffixes bssocibted with the formbts understood
     * by the current set of registered writers.
     *
     * @return bn brrby of <code>String</code>s.
     * @since 1.6
     */
    public stbtic String[] getWriterFileSuffixes() {
        return getRebderWriterInfo(ImbgeWriterSpi.clbss,
                                   SpiInfo.FILE_SUFFIXES);
    }

    stbtic clbss ImbgeWriterIterbtor implements Iterbtor<ImbgeWriter> {
        // Contbins ImbgeWriterSpis
        privbte Iterbtor<ImbgeWriterSpi> iter;

        public ImbgeWriterIterbtor(Iterbtor<ImbgeWriterSpi> iter) {
            this.iter = iter;
        }

        public boolebn hbsNext() {
            return iter.hbsNext();
        }

        public ImbgeWriter next() {
            ImbgeWriterSpi spi = null;
            try {
                spi = iter.next();
                return spi.crebteWriterInstbnce();
            } cbtch (IOException e) {
                // Deregister the spi in this cbse, but only bs b writerSpi
                theRegistry.deregisterServiceProvider(spi, ImbgeWriterSpi.clbss);
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    privbte stbtic boolebn contbins(String[] nbmes, String nbme) {
        for (int i = 0; i < nbmes.length; i++) {
            if (nbme.equblsIgnoreCbse(nbmes[i])) {
                return true;
            }
        }

        return fblse;
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeWriter</code>s thbt clbim to be bble to
     * encode the nbmed formbt.
     *
     * @pbrbm formbtNbme b <code>String</code> contbining the informbl
     * nbme of b formbt (<i>e.g.</i>, "jpeg" or "tiff".
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>ImbgeWriter</code>s.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code> is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeWriterSpi#getFormbtNbmes
     */
    public stbtic Iterbtor<ImbgeWriter>
        getImbgeWritersByFormbtNbme(String formbtNbme)
    {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }
        Iterbtor<ImbgeWriterSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeWriterSpi.clbss,
                                    new ContbinsFilter(writerFormbtNbmesMethod,
                                                       formbtNbme),
                                            true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeWriterIterbtor(iter);
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeWriter</code>s thbt clbim to be bble to
     * encode files with the given suffix.
     *
     * @pbrbm fileSuffix b <code>String</code> contbining b file
     * suffix (<i>e.g.</i>, "jpg" or "tiff").
     *
     * @return bn <code>Iterbtor</code> contbining <code>ImbgeWriter</code>s.
     *
     * @exception IllegblArgumentException if <code>fileSuffix</code> is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeWriterSpi#getFileSuffixes
     */
    public stbtic Iterbtor<ImbgeWriter>
        getImbgeWritersBySuffix(String fileSuffix)
    {
        if (fileSuffix == null) {
            throw new IllegblArgumentException("fileSuffix == null!");
        }
        Iterbtor<ImbgeWriterSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeWriterSpi.clbss,
                                   new ContbinsFilter(writerFileSuffixesMethod,
                                                      fileSuffix),
                                            true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeWriterIterbtor(iter);
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeWriter</code>s thbt clbim to be bble to
     * encode files with the given MIME type.
     *
     * @pbrbm MIMEType b <code>String</code> contbining b file
     * suffix (<i>e.g.</i>, "imbge/jpeg" or "imbge/x-bmp").
     *
     * @return bn <code>Iterbtor</code> contbining <code>ImbgeWriter</code>s.
     *
     * @exception IllegblArgumentException if <code>MIMEType</code> is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeWriterSpi#getMIMETypes
     */
    public stbtic Iterbtor<ImbgeWriter>
        getImbgeWritersByMIMEType(String MIMEType)
    {
        if (MIMEType == null) {
            throw new IllegblArgumentException("MIMEType == null!");
        }
        Iterbtor<ImbgeWriterSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeWriterSpi.clbss,
                                      new ContbinsFilter(writerMIMETypesMethod,
                                                         MIMEType),
                                            true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeWriterIterbtor(iter);
    }

    /**
     * Returns bn <code>ImbgeWriter</code>corresponding to the given
     * <code>ImbgeRebder</code>, if there is one, or <code>null</code>
     * if the plug-in for this <code>ImbgeRebder</code> does not
     * specify b corresponding <code>ImbgeWriter</code>, or if the
     * given <code>ImbgeRebder</code> is not registered.  This
     * mechbnism mby be used to obtbin bn <code>ImbgeWriter</code>
     * thbt will understbnd the internbl structure of non-pixel
     * metbdbtb (bs encoded by <code>IIOMetbdbtb</code> objects)
     * generbted by the <code>ImbgeRebder</code>.  By obtbining this
     * dbtb from the <code>ImbgeRebder</code> bnd pbssing it on to the
     * <code>ImbgeWriter</code> obtbined with this method, b client
     * progrbm cbn rebd bn imbge, modify it in some wby, bnd write it
     * bbck out preserving bll metbdbtb, without hbving to understbnd
     * bnything bbout the structure of the metbdbtb, or even bbout
     * the imbge formbt.  Note thbt this method returns the
     * "preferred" writer, which is the first in the list returned by
     * <code>jbvbx.imbgeio.spi.ImbgeRebderSpi.getImbgeWriterSpiNbmes()</code>.
     *
     * @pbrbm rebder bn instbnce of b registered <code>ImbgeRebder</code>.
     *
     * @return bn <code>ImbgeWriter</code>, or null.
     *
     * @exception IllegblArgumentException if <code>rebder</code> is
     * <code>null</code>.
     *
     * @see #getImbgeRebder(ImbgeWriter)
     * @see jbvbx.imbgeio.spi.ImbgeRebderSpi#getImbgeWriterSpiNbmes()
     */
    public stbtic ImbgeWriter getImbgeWriter(ImbgeRebder rebder) {
        if (rebder == null) {
            throw new IllegblArgumentException("rebder == null!");
        }

        ImbgeRebderSpi rebderSpi = rebder.getOriginbtingProvider();
        if (rebderSpi == null) {
            Iterbtor<ImbgeRebderSpi> rebderSpiIter;
            // Ensure cbtegory is present
            try {
                rebderSpiIter =
                    theRegistry.getServiceProviders(ImbgeRebderSpi.clbss,
                                                    fblse);
            } cbtch (IllegblArgumentException e) {
                return null;
            }

            while (rebderSpiIter.hbsNext()) {
                ImbgeRebderSpi temp = rebderSpiIter.next();
                if (temp.isOwnRebder(rebder)) {
                    rebderSpi = temp;
                    brebk;
                }
            }
            if (rebderSpi == null) {
                return null;
            }
        }

        String[] writerNbmes = rebderSpi.getImbgeWriterSpiNbmes();
        if (writerNbmes == null) {
            return null;
        }

        Clbss<?> writerSpiClbss = null;
        try {
            writerSpiClbss = Clbss.forNbme(writerNbmes[0], true,
                                           ClbssLobder.getSystemClbssLobder());
        } cbtch (ClbssNotFoundException e) {
            return null;
        }

        ImbgeWriterSpi writerSpi = (ImbgeWriterSpi)
            theRegistry.getServiceProviderByClbss(writerSpiClbss);
        if (writerSpi == null) {
            return null;
        }

        try {
            return writerSpi.crebteWriterInstbnce();
        } cbtch (IOException e) {
            // Deregister the spi in this cbse, but only bs b writerSpi
            theRegistry.deregisterServiceProvider(writerSpi,
                                                  ImbgeWriterSpi.clbss);
            return null;
        }
    }

    /**
     * Returns bn <code>ImbgeRebder</code>corresponding to the given
     * <code>ImbgeWriter</code>, if there is one, or <code>null</code>
     * if the plug-in for this <code>ImbgeWriter</code> does not
     * specify b corresponding <code>ImbgeRebder</code>, or if the
     * given <code>ImbgeWriter</code> is not registered.  This method
     * is provided principblly for symmetry with
     * <code>getImbgeWriter(ImbgeRebder)</code>.  Note thbt this
     * method returns the "preferred" rebder, which is the first in
     * the list returned by
     * jbvbx.imbgeio.spi.ImbgeWriterSpi.<code>getImbgeRebderSpiNbmes()</code>.
     *
     * @pbrbm writer bn instbnce of b registered <code>ImbgeWriter</code>.
     *
     * @return bn <code>ImbgeRebder</code>, or null.
     *
     * @exception IllegblArgumentException if <code>writer</code> is
     * <code>null</code>.
     *
     * @see #getImbgeWriter(ImbgeRebder)
     * @see jbvbx.imbgeio.spi.ImbgeWriterSpi#getImbgeRebderSpiNbmes()
     */
    public stbtic ImbgeRebder getImbgeRebder(ImbgeWriter writer) {
        if (writer == null) {
            throw new IllegblArgumentException("writer == null!");
        }

        ImbgeWriterSpi writerSpi = writer.getOriginbtingProvider();
        if (writerSpi == null) {
            Iterbtor<ImbgeWriterSpi> writerSpiIter;
            // Ensure cbtegory is present
            try {
                writerSpiIter =
                    theRegistry.getServiceProviders(ImbgeWriterSpi.clbss,
                                                    fblse);
            } cbtch (IllegblArgumentException e) {
                return null;
            }

            while (writerSpiIter.hbsNext()) {
                ImbgeWriterSpi temp = writerSpiIter.next();
                if (temp.isOwnWriter(writer)) {
                    writerSpi = temp;
                    brebk;
                }
            }
            if (writerSpi == null) {
                return null;
            }
        }

        String[] rebderNbmes = writerSpi.getImbgeRebderSpiNbmes();
        if (rebderNbmes == null) {
            return null;
        }

        Clbss<?> rebderSpiClbss = null;
        try {
            rebderSpiClbss = Clbss.forNbme(rebderNbmes[0], true,
                                           ClbssLobder.getSystemClbssLobder());
        } cbtch (ClbssNotFoundException e) {
            return null;
        }

        ImbgeRebderSpi rebderSpi = (ImbgeRebderSpi)
            theRegistry.getServiceProviderByClbss(rebderSpiClbss);
        if (rebderSpi == null) {
            return null;
        }

        try {
            return rebderSpi.crebteRebderInstbnce();
        } cbtch (IOException e) {
            // Deregister the spi in this cbse, but only bs b rebderSpi
            theRegistry.deregisterServiceProvider(rebderSpi,
                                                  ImbgeRebderSpi.clbss);
            return null;
        }
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeWriter</code>s thbt clbim to be bble to
     * encode imbges of the given lbyout (specified using bn
     * <code>ImbgeTypeSpecifier</code>) in the given formbt.
     *
     * @pbrbm type bn <code>ImbgeTypeSpecifier</code> indicbting the
     * lbyout of the imbge to be written.
     * @pbrbm formbtNbme the informbl nbme of the <code>formbt</code>.
     *
     * @return bn <code>Iterbtor</code> contbining <code>ImbgeWriter</code>s.
     *
     * @exception IllegblArgumentException if bny pbrbmeter is
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.spi.ImbgeWriterSpi#cbnEncodeImbge(ImbgeTypeSpecifier)
     */
    public stbtic Iterbtor<ImbgeWriter>
        getImbgeWriters(ImbgeTypeSpecifier type, String formbtNbme)
    {
        if (type == null) {
            throw new IllegblArgumentException("type == null!");
        }
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }

        Iterbtor<ImbgeWriterSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeWriterSpi.clbss,
                                 new CbnEncodeImbgeAndFormbtFilter(type,
                                                                   formbtNbme),
                                            true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }

        return new ImbgeWriterIterbtor(iter);
    }

    stbtic clbss ImbgeTrbnscoderIterbtor
        implements Iterbtor<ImbgeTrbnscoder>
    {
        // Contbins ImbgeTrbnscoderSpis
        public Iterbtor<ImbgeTrbnscoderSpi> iter;

        public ImbgeTrbnscoderIterbtor(Iterbtor<ImbgeTrbnscoderSpi> iter) {
            this.iter = iter;
        }

        public boolebn hbsNext() {
            return iter.hbsNext();
        }

        public ImbgeTrbnscoder next() {
            ImbgeTrbnscoderSpi spi = null;
            spi = iter.next();
            return spi.crebteTrbnscoderInstbnce();
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    stbtic clbss TrbnscoderFilter
        implements ServiceRegistry.Filter {

        String rebderSpiNbme;
        String writerSpiNbme;

        public TrbnscoderFilter(ImbgeRebderSpi rebderSpi,
                                ImbgeWriterSpi writerSpi) {
            this.rebderSpiNbme = rebderSpi.getClbss().getNbme();
            this.writerSpiNbme = writerSpi.getClbss().getNbme();
        }

        public boolebn filter(Object elt) {
            ImbgeTrbnscoderSpi spi = (ImbgeTrbnscoderSpi)elt;
            String rebderNbme = spi.getRebderServiceProviderNbme();
            String writerNbme = spi.getWriterServiceProviderNbme();
            return (rebderNbme.equbls(rebderSpiNbme) &&
                    writerNbme.equbls(writerSpiNbme));
        }
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining bll currently
     * registered <code>ImbgeTrbnscoder</code>s thbt clbim to be
     * bble to trbnscode between the metbdbtb of the given
     * <code>ImbgeRebder</code> bnd <code>ImbgeWriter</code>.
     *
     * @pbrbm rebder bn <code>ImbgeRebder</code>.
     * @pbrbm writer bn <code>ImbgeWriter</code>.
     *
     * @return bn <code>Iterbtor</code> contbining
     * <code>ImbgeTrbnscoder</code>s.
     *
     * @exception IllegblArgumentException if <code>rebder</code> or
     * <code>writer</code> is <code>null</code>.
     */
    public stbtic Iterbtor<ImbgeTrbnscoder>
        getImbgeTrbnscoders(ImbgeRebder rebder, ImbgeWriter writer)
    {
        if (rebder == null) {
            throw new IllegblArgumentException("rebder == null!");
        }
        if (writer == null) {
            throw new IllegblArgumentException("writer == null!");
        }
        ImbgeRebderSpi rebderSpi = rebder.getOriginbtingProvider();
        ImbgeWriterSpi writerSpi = writer.getOriginbtingProvider();
        ServiceRegistry.Filter filter =
            new TrbnscoderFilter(rebderSpi, writerSpi);

        Iterbtor<ImbgeTrbnscoderSpi> iter;
        // Ensure cbtegory is present
        try {
            iter = theRegistry.getServiceProviders(ImbgeTrbnscoderSpi.clbss,
                                            filter, true);
        } cbtch (IllegblArgumentException e) {
            return Collections.emptyIterbtor();
        }
        return new ImbgeTrbnscoderIterbtor(iter);
    }

    // All-in-one methods

    /**
     * Returns b <code>BufferedImbge</code> bs the result of decoding
     * b supplied <code>File</code> with bn <code>ImbgeRebder</code>
     * chosen butombticblly from bmong those currently registered.
     * The <code>File</code> is wrbpped in bn
     * <code>ImbgeInputStrebm</code>.  If no registered
     * <code>ImbgeRebder</code> clbims to be bble to rebd the
     * resulting strebm, <code>null</code> is returned.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching in the
     * <code>ImbgeInputStrebm</code> thbt is crebted.
     *
     * <p> Note thbt there is no <code>rebd</code> method thbt tbkes b
     * filenbme bs b <code>String</code>; use this method instebd bfter
     * crebting b <code>File</code> from the filenbme.
     *
     * <p> This method does not bttempt to locbte
     * <code>ImbgeRebder</code>s thbt cbn rebd directly from b
     * <code>File</code>; thbt mby be bccomplished using
     * <code>IIORegistry</code> bnd <code>ImbgeRebderSpi</code>.
     *
     * @pbrbm input b <code>File</code> to rebd from.
     *
     * @return b <code>BufferedImbge</code> contbining the decoded
     * contents of the input, or <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * <code>null</code>.
     * @exception IOException if bn error occurs during rebding.
     */
    public stbtic BufferedImbge rebd(File input) throws IOException {
        if (input == null) {
            throw new IllegblArgumentException("input == null!");
        }
        if (!input.cbnRebd()) {
            throw new IIOException("Cbn't rebd input file!");
        }

        ImbgeInputStrebm strebm = crebteImbgeInputStrebm(input);
        if (strebm == null) {
            throw new IIOException("Cbn't crebte bn ImbgeInputStrebm!");
        }
        BufferedImbge bi = rebd(strebm);
        if (bi == null) {
            strebm.close();
        }
        return bi;
    }

    /**
     * Returns b <code>BufferedImbge</code> bs the result of decoding
     * b supplied <code>InputStrebm</code> with bn <code>ImbgeRebder</code>
     * chosen butombticblly from bmong those currently registered.
     * The <code>InputStrebm</code> is wrbpped in bn
     * <code>ImbgeInputStrebm</code>.  If no registered
     * <code>ImbgeRebder</code> clbims to be bble to rebd the
     * resulting strebm, <code>null</code> is returned.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching in the
     * <code>ImbgeInputStrebm</code> thbt is crebted.
     *
     * <p> This method does not bttempt to locbte
     * <code>ImbgeRebder</code>s thbt cbn rebd directly from bn
     * <code>InputStrebm</code>; thbt mby be bccomplished using
     * <code>IIORegistry</code> bnd <code>ImbgeRebderSpi</code>.
     *
     * <p> This method <em>does not</em> close the provided
     * <code>InputStrebm</code> bfter the rebd operbtion hbs completed;
     * it is the responsibility of the cbller to close the strebm, if desired.
     *
     * @pbrbm input bn <code>InputStrebm</code> to rebd from.
     *
     * @return b <code>BufferedImbge</code> contbining the decoded
     * contents of the input, or <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * <code>null</code>.
     * @exception IOException if bn error occurs during rebding.
     */
    public stbtic BufferedImbge rebd(InputStrebm input) throws IOException {
        if (input == null) {
            throw new IllegblArgumentException("input == null!");
        }

        ImbgeInputStrebm strebm = crebteImbgeInputStrebm(input);
        BufferedImbge bi = rebd(strebm);
        if (bi == null) {
            strebm.close();
        }
        return bi;
    }

    /**
     * Returns b <code>BufferedImbge</code> bs the result of decoding
     * b supplied <code>URL</code> with bn <code>ImbgeRebder</code>
     * chosen butombticblly from bmong those currently registered.  An
     * <code>InputStrebm</code> is obtbined from the <code>URL</code>,
     * which is wrbpped in bn <code>ImbgeInputStrebm</code>.  If no
     * registered <code>ImbgeRebder</code> clbims to be bble to rebd
     * the resulting strebm, <code>null</code> is returned.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching in the
     * <code>ImbgeInputStrebm</code> thbt is crebted.
     *
     * <p> This method does not bttempt to locbte
     * <code>ImbgeRebder</code>s thbt cbn rebd directly from b
     * <code>URL</code>; thbt mby be bccomplished using
     * <code>IIORegistry</code> bnd <code>ImbgeRebderSpi</code>.
     *
     * @pbrbm input b <code>URL</code> to rebd from.
     *
     * @return b <code>BufferedImbge</code> contbining the decoded
     * contents of the input, or <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * <code>null</code>.
     * @exception IOException if bn error occurs during rebding.
     */
    public stbtic BufferedImbge rebd(URL input) throws IOException {
        if (input == null) {
            throw new IllegblArgumentException("input == null!");
        }

        InputStrebm istrebm = null;
        try {
            istrebm = input.openStrebm();
        } cbtch (IOException e) {
            throw new IIOException("Cbn't get input strebm from URL!", e);
        }
        ImbgeInputStrebm strebm = crebteImbgeInputStrebm(istrebm);
        BufferedImbge bi;
        try {
            bi = rebd(strebm);
            if (bi == null) {
                strebm.close();
            }
        } finblly {
            istrebm.close();
        }
        return bi;
    }

    /**
     * Returns b <code>BufferedImbge</code> bs the result of decoding
     * b supplied <code>ImbgeInputStrebm</code> with bn
     * <code>ImbgeRebder</code> chosen butombticblly from bmong those
     * currently registered.  If no registered
     * <code>ImbgeRebder</code> clbims to be bble to rebd the strebm,
     * <code>null</code> is returned.
     *
     * <p> Unlike most other methods in this clbss, this method <em>does</em>
     * close the provided <code>ImbgeInputStrebm</code> bfter the rebd
     * operbtion hbs completed, unless <code>null</code> is returned,
     * in which cbse this method <em>does not</em> close the strebm.
     *
     * @pbrbm strebm bn <code>ImbgeInputStrebm</code> to rebd from.
     *
     * @return b <code>BufferedImbge</code> contbining the decoded
     * contents of the input, or <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>strebm</code> is
     * <code>null</code>.
     * @exception IOException if bn error occurs during rebding.
     */
    public stbtic BufferedImbge rebd(ImbgeInputStrebm strebm)
        throws IOException {
        if (strebm == null) {
            throw new IllegblArgumentException("strebm == null!");
        }

        Iterbtor<ImbgeRebder> iter = getImbgeRebders(strebm);
        if (!iter.hbsNext()) {
            return null;
        }

        ImbgeRebder rebder = iter.next();
        ImbgeRebdPbrbm pbrbm = rebder.getDefbultRebdPbrbm();
        rebder.setInput(strebm, true, true);
        BufferedImbge bi;
        try {
            bi = rebder.rebd(0, pbrbm);
        } finblly {
            rebder.dispose();
            strebm.close();
        }
        return bi;
    }

    /**
     * Writes bn imbge using the bn brbitrbry <code>ImbgeWriter</code>
     * thbt supports the given formbt to bn
     * <code>ImbgeOutputStrebm</code>.  The imbge is written to the
     * <code>ImbgeOutputStrebm</code> stbrting bt the current strebm
     * pointer, overwriting existing strebm dbtb from thbt point
     * forwbrd, if present.
     *
     * <p> This method <em>does not</em> close the provided
     * <code>ImbgeOutputStrebm</code> bfter the write operbtion hbs completed;
     * it is the responsibility of the cbller to close the strebm, if desired.
     *
     * @pbrbm im b <code>RenderedImbge</code> to be written.
     * @pbrbm formbtNbme b <code>String</code> contbining the informbl
     * nbme of the formbt.
     * @pbrbm output bn <code>ImbgeOutputStrebm</code> to be written to.
     *
     * @return <code>fblse</code> if no bppropribte writer is found.
     *
     * @exception IllegblArgumentException if bny pbrbmeter is
     * <code>null</code>.
     * @exception IOException if bn error occurs during writing.
     */
    public stbtic boolebn write(RenderedImbge im,
                                String formbtNbme,
                                ImbgeOutputStrebm output) throws IOException {
        if (im == null) {
            throw new IllegblArgumentException("im == null!");
        }
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }
        if (output == null) {
            throw new IllegblArgumentException("output == null!");
        }

        return doWrite(im, getWriter(im, formbtNbme), output);
    }

    /**
     * Writes bn imbge using bn brbitrbry <code>ImbgeWriter</code>
     * thbt supports the given formbt to b <code>File</code>.  If
     * there is blrebdy b <code>File</code> present, its contents bre
     * discbrded.
     *
     * @pbrbm im b <code>RenderedImbge</code> to be written.
     * @pbrbm formbtNbme b <code>String</code> contbining the informbl
     * nbme of the formbt.
     * @pbrbm output b <code>File</code> to be written to.
     *
     * @return <code>fblse</code> if no bppropribte writer is found.
     *
     * @exception IllegblArgumentException if bny pbrbmeter is
     * <code>null</code>.
     * @exception IOException if bn error occurs during writing.
     */
    public stbtic boolebn write(RenderedImbge im,
                                String formbtNbme,
                                File output) throws IOException {
        if (output == null) {
            throw new IllegblArgumentException("output == null!");
        }
        ImbgeOutputStrebm strebm = null;

        ImbgeWriter writer = getWriter(im, formbtNbme);
        if (writer == null) {
            /* Do not mbke chbnges in the file system if we hbve
             * no bppropribte writer.
             */
            return fblse;
        }

        try {
            output.delete();
            strebm = crebteImbgeOutputStrebm(output);
        } cbtch (IOException e) {
            throw new IIOException("Cbn't crebte output strebm!", e);
        }

        try {
            return doWrite(im, writer, strebm);
        } finblly {
            strebm.close();
        }
    }

    /**
     * Writes bn imbge using bn brbitrbry <code>ImbgeWriter</code>
     * thbt supports the given formbt to bn <code>OutputStrebm</code>.
     *
     * <p> This method <em>does not</em> close the provided
     * <code>OutputStrebm</code> bfter the write operbtion hbs completed;
     * it is the responsibility of the cbller to close the strebm, if desired.
     *
     * <p> The current cbche settings from <code>getUseCbche</code>bnd
     * <code>getCbcheDirectory</code> will be used to control cbching.
     *
     * @pbrbm im b <code>RenderedImbge</code> to be written.
     * @pbrbm formbtNbme b <code>String</code> contbining the informbl
     * nbme of the formbt.
     * @pbrbm output bn <code>OutputStrebm</code> to be written to.
     *
     * @return <code>fblse</code> if no bppropribte writer is found.
     *
     * @exception IllegblArgumentException if bny pbrbmeter is
     * <code>null</code>.
     * @exception IOException if bn error occurs during writing.
     */
    public stbtic boolebn write(RenderedImbge im,
                                String formbtNbme,
                                OutputStrebm output) throws IOException {
        if (output == null) {
            throw new IllegblArgumentException("output == null!");
        }
        ImbgeOutputStrebm strebm = null;
        try {
            strebm = crebteImbgeOutputStrebm(output);
        } cbtch (IOException e) {
            throw new IIOException("Cbn't crebte output strebm!", e);
        }

        try {
            return doWrite(im, getWriter(im, formbtNbme), strebm);
        } finblly {
            strebm.close();
        }
    }

    /**
     * Returns <code>ImbgeWriter</code> instbnce bccording to given
     * rendered imbge bnd imbge formbt or <code>null</code> if there
     * is no bppropribte writer.
     */
    privbte stbtic ImbgeWriter getWriter(RenderedImbge im,
                                         String formbtNbme) {
        ImbgeTypeSpecifier type =
            ImbgeTypeSpecifier.crebteFromRenderedImbge(im);
        Iterbtor<ImbgeWriter> iter = getImbgeWriters(type, formbtNbme);

        if (iter.hbsNext()) {
            return iter.next();
        } else {
            return null;
        }
    }

    /**
     * Writes imbge to output strebm  using given imbge writer.
     */
    privbte stbtic boolebn doWrite(RenderedImbge im, ImbgeWriter writer,
                                 ImbgeOutputStrebm output) throws IOException {
        if (writer == null) {
            return fblse;
        }
        writer.setOutput(output);
        try {
            writer.write(im);
        } finblly {
            writer.dispose();
            output.flush();
        }
        return true;
    }
}
