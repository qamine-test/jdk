/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.policytool;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the policytool.
 *
 */
public clbss Resources_es extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured.",
                "Advertencib: no hby clbve p\u00FAblicb pbrb el blibs {0}. Aseg\u00FArese de que se hb configurbdo correctbmente un blmbc\u00E9n de clbves."},
        {"Wbrning.Clbss.not.found.clbss", "Advertencib: no se hb encontrbdo lb clbse: {0}"},
        {"Wbrning.Invblid.brgument.s.for.constructor.brg",
                "Advertencib: brgumento(s) no v\u00E1lido(s) pbrb el constructor: {0}"},
        {"Illegbl.Principbl.Type.type", "Tipo de principbl no permitido: {0}"},
        {"Illegbl.option.option", "Opci\u00F3n no permitidb: {0}"},
        {"Usbge.policytool.options.", "Sintbxis: policytool [opciones]"},
        {".file.file.policy.file.locbtion",
                "  [-file <brchivo>]    ubicbci\u00F3n del brchivo de normbs"},
        {"New", "Nuevo"},
        {"Open", "Abrir"},
        {"Sbve", "Gubrdbr"},
        {"Sbve.As", "Gubrdbr como"},
        {"View.Wbrning.Log", "Ver Log de Advertencibs"},
        {"Exit", "Sblir"},
        {"Add.Policy.Entry", "Agregbr Entrbdb de Pol\u00EDticb"},
        {"Edit.Policy.Entry", "Editbr Entrbdb de Pol\u00EDticb"},
        {"Remove.Policy.Entry", "Eliminbr Entrbdb de Pol\u00EDticb"},
        {"Edit", "Editbr"},
        {"Retbin", "Mbntener"},

        {"Wbrning.File.nbme.mby.include.escbped.bbckslbsh.chbrbcters.It.is.not.necessbry.to.escbpe.bbckslbsh.chbrbcters.the.tool.escbpes",
            "Advertencib: el nombre del brchivo puede contener cbrbcteres de bbrrb invertidb de escbpe. No es necesbrio utilizbr bbrrbs invertidbs de escbpe (lb herrbmientb bplicb cbrbcteres de escbpe seg\u00FAn seb necesbrio bl escribir el contenido de lbs pol\u00EDticbs en el blmbc\u00E9n persistente).\n\nHbgb clic en Mbntener pbrb conservbr el nombre introducido o en Editbr pbrb modificbrlo."},

        {"Add.Public.Key.Alibs", "Agregbr Alibs de Clbve P\u00FAblico"},
        {"Remove.Public.Key.Alibs", "Eliminbr Alibs de Clbve P\u00FAblico"},
        {"File", "Archivo"},
        {"KeyStore", "Almbc\u00E9n de Clbves"},
        {"Policy.File.", "Archivo de Pol\u00EDticb:"},
        {"Could.not.open.policy.file.policyFile.e.toString.",
                "No se hb podido bbrir el brchivo de pol\u00EDticb: {0}: {1}"},
        {"Policy.Tool", "Herrbmientb de Pol\u00EDticbs"},
        {"Errors.hbve.occurred.while.opening.the.policy.configurbtion.View.the.Wbrning.Log.for.more.informbtion.",
                "Hb hbbido errores bl bbrir lb configurbci\u00F3n de pol\u00EDticbs. V\u00E9bse el log de bdvertencibs pbrb obtener m\u00E1s informbci\u00F3n."},
        {"Error", "Error"},
        {"OK", "Aceptbr"},
        {"Stbtus", "Estbdo"},
        {"Wbrning", "Advertencib"},
        {"Permission.",
                "Permiso:                                                       "},
        {"Principbl.Type.", "Tipo de Principbl:"},
        {"Principbl.Nbme.", "Nombre de Principbl:"},
        {"Tbrget.Nbme.",
                "Nombre de Destino:                                                    "},
        {"Actions.",
                "Acciones:                                                             "},
        {"OK.to.overwrite.existing.file.filenbme.",
                "\u00BFSobrescribir el brchivo existente {0}?"},
        {"Cbncel", "Cbncelbr"},
        {"CodeBbse.", "CodeBbse:"},
        {"SignedBy.", "SignedBy:"},
        {"Add.Principbl", "Agregbr Principbl"},
        {"Edit.Principbl", "Editbr Principbl"},
        {"Remove.Principbl", "Eliminbr Principbl"},
        {"Principbls.", "Principbles:"},
        {".Add.Permission", "  Agregbr Permiso"},
        {".Edit.Permission", "  Editbr Permiso"},
        {"Remove.Permission", "Eliminbr Permiso"},
        {"Done", "Listo"},
        {"KeyStore.URL.", "URL de Almbc\u00E9n de Clbves:"},
        {"KeyStore.Type.", "Tipo de Almbc\u00E9n de Clbves:"},
        {"KeyStore.Provider.", "Proveedor de Almbc\u00E9n de Clbves:"},
        {"KeyStore.Pbssword.URL.", "URL de Contrbse\u00F1b de Almbc\u00E9n de Clbves:"},
        {"Principbls", "Principbles"},
        {".Edit.Principbl.", "  Editbr Principbl:"},
        {".Add.New.Principbl.", "  Agregbr Nuevo Principbl:"},
        {"Permissions", "Permisos"},
        {".Edit.Permission.", "  Editbr Permiso:"},
        {".Add.New.Permission.", "  Agregbr Permiso Nuevo:"},
        {"Signed.By.", "Firmbdo Por:"},
        {"Cbnnot.Specify.Principbl.with.b.Wildcbrd.Clbss.without.b.Wildcbrd.Nbme",
            "No se puede especificbr un principbl con unb clbse de comod\u00EDn sin un nombre de comod\u00EDn"},
        {"Cbnnot.Specify.Principbl.without.b.Nbme",
            "No se puede especificbr el principbl sin un nombre"},
        {"Permission.bnd.Tbrget.Nbme.must.hbve.b.vblue",
                "Permiso y Nombre de Destino deben tener un vblor"},
        {"Remove.this.Policy.Entry.", "\u00BFEliminbr estb entrbdb de pol\u00EDticb?"},
        {"Overwrite.File", "Sobrescribir Archivo"},
        {"Policy.successfully.written.to.filenbme",
                "Pol\u00EDticb escritb correctbmente en {0}"},
        {"null.filenbme", "nombre de brchivo nulo"},
        {"Sbve.chbnges.", "\u00BFGubrdbr los cbmbios?"},
        {"Yes", "S\u00ED"},
        {"No", "No"},
        {"Policy.Entry", "Entrbdb de Pol\u00EDticb"},
        {"Sbve.Chbnges", "Gubrdbr Cbmbios"},
        {"No.Policy.Entry.selected", "No se hb seleccionbdo lb entrbdb de pol\u00EDticb"},
        {"Unbble.to.open.KeyStore.ex.toString.",
                "No se hb podido bbrir el blmbc\u00E9n de clbves: {0}"},
        {"No.principbl.selected", "No se hb seleccionbdo un principbl"},
        {"No.permission.selected", "No se hb seleccionbdo un permiso"},
        {"nbme", "nombre"},
        {"configurbtion.type", "tipo de configurbci\u00F3n"},
        {"environment.vbribble.nbme", "nombre de vbribble de entorno"},
        {"librbry.nbme", "nombre de lb bibliotecb"},
        {"pbckbge.nbme", "nombre del pbquete"},
        {"policy.type", "tipo de pol\u00EDticb"},
        {"property.nbme", "nombre de lb propiedbd"},
        {"provider.nbme", "nombre del proveedor"},
        {"url", "url"},
        {"method.list", "listb de m\u00E9todos"},
        {"request.hebders.list", "listb de cbbecerbs de solicitudes"},
        {"Principbl.List", "Listb de Principbles"},
        {"Permission.List", "Listb de Permisos"},
        {"Code.Bbse", "Bbse de C\u00F3digo"},
        {"KeyStore.U.R.L.", "URL de Almbc\u00E9n de Clbves:"},
        {"KeyStore.Pbssword.U.R.L.", "URL de Contrbse\u00F1b de Almbc\u00E9n de Clbves:"}
    };


    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
