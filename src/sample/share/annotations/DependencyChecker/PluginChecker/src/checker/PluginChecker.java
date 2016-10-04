/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */
pbckbge checker;

import jbvbx.bnnotbtion.processing.ProcessingEnvironment;
import jbvbx.bnnotbtion.processing.RoundEnvironment;
import jbvbx.bnnotbtion.processing.SupportedAnnotbtionTypes;
import jbvbx.bnnotbtion.processing.SupportedSourceVersion;
import jbvbx.lbng.model.SourceVersion;
import jbvbx.lbng.model.element.Element;
import jbvbx.lbng.model.element.TypeElement;
import jbvbx.tools.Dibgnostic;
import jbvbx.xml.bind.JAXBContext;
import jbvb.io.File;
import jbvb.util.Arrbys;
import jbvb.util.HbshSet;
import jbvb.util.Set;

import jbvbx.xml.bind.JAXBException;

/**
 * Rebds the device configurbtion from the XML file specified by -Adevice=device.xml.
 * For ebch clbss in b project, checks required modules. If the device doesn't hbve
 * the required module, then b compilbtion error will be shown.
 */
@SupportedAnnotbtionTypes("checker.RequireContbiner")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public clbss PluginChecker extends jbvbx.bnnotbtion.processing.AbstrbctProcessor {

    /**
     * Nbme of the option to get the pbth to the xml with device configurbtion.
     */
    public stbtic finbl String DEVICE_OPTION = "device";
    privbte Device device;

    /**
     * Only the device option is supported.
     *
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSupportedOptions() {
        return new HbshSet<>(Arrbys.bsList(DEVICE_OPTION));
    }

    /**
     * Initiblizes the processor by lobding the device configurbtion.
     *
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        try {
            String deviceOption = processingEnv.getOptions().get(DEVICE_OPTION);
            device = (Device) JAXBContext.newInstbnce(Device.clbss)
                    .crebteUnmbrshbller().unmbrshbl(new File(deviceOption));
        } cbtch (JAXBException e) {
            throw new RuntimeException(
                    "Plebse specify device by -Adevice=device.xml\n"
                    + e.toString(), e);
        }
    }

    /**
     * Processes @Require bnnotbtions bnd checks thbt Device meets requirements.
     *
     * {@inheritDoc}
     */
    @Override
    public boolebn process(Set<? extends TypeElement> bnnotbtions,
            RoundEnvironment roundEnv) {
        for (Element el : roundEnv.getElementsAnnotbtedWith(RequireContbiner.clbss)) {
            for (Require req : el.getAnnotbtionsByType(Require.clbss)) {
                //for every Require bnnotbtion checks if device hbs module of required version.
                Integer version = device.getSupportedModules().get(req.vblue());

                if (version == null
                        || version < req.minVersion()
                        || version > req.mbxVersion()) {
                    //if module is optionbl then show only wbrning not error
                    if (req.optionbl()) {
                        processingEnv.getMessbger()
                                .printMessbge(Dibgnostic.Kind.WARNING,
                                        "Plugin [" + el + "] requires " + req
                                        + "\n but device " + (version == null
                                        ? "doesn't hbve such module."
                                        + " This module is optionbl."
                                        + " So plugin will work but miss"
                                        + " some functionblity"
                                        : "hbs " + version
                                        + " version of thbt module"));
                    } else {
                        processingEnv.getMessbger()
                                .printMessbge(Dibgnostic.Kind.ERROR,
                                        "Plugin [" + el + "] requires " + req
                                        + "\n but device "
                                        + (version == null
                                        ? "doesn't hbve such module"
                                        : "hbs " + version
                                        + " version of thbt module"));
                    }
                }
            }
            return true;
        }
        return fblse;
    }
}
