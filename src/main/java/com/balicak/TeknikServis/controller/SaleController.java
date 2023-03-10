package com.balicak.TeknikServis.controller;

import com.balicak.TeknikServis.model.Sale;
import com.balicak.TeknikServis.service.SaleService;
import com.balicak.TeknikServis.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping (path = "sale")
@Tag (name = "Sale Service", description = "This class contains information of the sales.")
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SaleController {

    private SaleService service;
    private MessageSource messageSource;

    public SaleController (SaleService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource= messageSource;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Secured("ROLE_ADMIN")
    @GetMapping ("getAll")
    @Operation (summary  = "This is to fetch all the sales stored in db.")
    public ResponseEntity< List<Sale> > getAll () {

        // localhost:9090/sale/getAll
        List< Sale > result = service.getAll();
        if ( result == null || result.size() == 0 ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation (summary = "This method is to delete the sales that saved with ID in database.")
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured("ROLE_ADMIN")
    @DeleteMapping (path = "delete/{id}")
    public ResponseEntity< String > deleteById (@Parameter(name = "SALE ID", required = true)@PathVariable (value = "id") Integer id,@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        // localhost:9090/sale/delete/2
        if ( service.deleteById(id) ) {
            return ResponseEntity.ok(messageSource.getMessage("sale.admin.delete.success", null, locale));
        } else {
            return ResponseEntity.internalServerError().body(messageSource.getMessage("sale.admin.delete.fail", null, locale));
        }
    }

    @Operation (summary = "This method is to save the sale to database.", responses = @ApiResponse(responseCode = "201 Created"))
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured("ROLE_ADMIN")
    @PostMapping (path = "save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< String > save (@RequestBody Sale sale,@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        // localhost:9090/sale/save
        // {"price" : 500,  "note" : "notee note ", "product_ID":1,"is_SOLD": true}
        if ( service.save(sale) ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(messageSource.getMessage("sale.admin.save.success", null, locale));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("sale.admin.save.fail", null, locale));
        }
    }
    // user controller alan??:
    @GetMapping("getListofSales")
    @Secured(value = "ROLE_USER")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Sat????ta olan ??r??nlerin listesini alan endpoint.",
            description = "??stekte bulunuldu??unda sistemdeki sat????a uygun olan ??r??nlerin t??m listesini getirir.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ba??ar??l?? bir ??ekilde veriler al??nd??????nda verilecek response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "403",
                            description = "??stekte bulunmaya yetkiniz yoksa alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "418",
                            description = "Sistemde sat??n al??ma uygun ??r??n bulunmad??????nda alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }

                    )
            }
    )
    public ResponseEntity getListofSales(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(RestResponse.of(service.getListofSales(locale)));
    }

    @GetMapping("getListofSalesByProduct")
    @Secured(value = "ROLE_USER")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Sat????ta olan ??r??nlerin par??a tipine g??re listesini alabilece??iniz endpoint.",
            description = "??stekte bulunuldu??unda sistemdeki sat????a uygun olan ??r??nlerin par??a t??r??ne g??re (Like Sorgusu) listesini getirir.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ba??ar??l?? bir ??ekilde veriler al??nd??????nda verilecek response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "403",
                            description = "??stekte bulunmaya yetkiniz yoksa alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "418",
                            description = "Sistemde sat??n al??ma uygun ??r??n bulunmad??????nda alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }

                    )
            }
    )
    public ResponseEntity getListofSalesByProduct(@RequestParam(value = "type") String productType, @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(RestResponse.of(service.getListofSalesByProduct(productType, locale)));
    }

    @GetMapping("getListofSalesByProductId")
    @Secured(value = "ROLE_USER")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Sat????ta olan ??r??nlerin par??a tipine g??re listesini alabilece??iniz endpoint.",
            description = "??stekte bulunuldu??unda sistemdeki sat????a uygun olan ??r??nlerin par??a t??r??ne g??re (Like Sorgusu) listesini getirir.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ba??ar??l?? bir ??ekilde veriler al??nd??????nda verilecek response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "403",
                            description = "??stekte bulunmaya yetkiniz yoksa alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "418",
                            description = "Sistemde sat??n al??ma uygun ??r??n bulunmad??????nda alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }

                    )
            }
    )
    public ResponseEntity getListofSalesByProductId(@RequestParam(value = "product_id") Integer productId, @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(RestResponse.of(service.getListofSalesByProductId(productId, locale)));
    }

    @PostMapping("buyTheProductInAd")
    @Secured(value = "ROLE_USER")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Sat????ta olan ??r??nlerin al??m i??lemi i??in kullan??l??r.",
            description = "??stekte bulunuldu??unda sistemdeki sat????a uygun olan ??r??nlerin t??m listesini getirir.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ba??ar??l?? bir ??ekilde veriler al??nd??????nda verilecek response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "403",
                            description = "??stekte bulunmaya yetkiniz yoksa alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }
                    ),
                    @ApiResponse(responseCode = "418",
                            description = "Sistemde sat??n almaya uygun ??r??n bulunmad??????nda alaca????n??z response status kodu.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RestResponse.class)))
                            }

                    )
            }
    )

    public ResponseEntity buyTheProductInAd(@Valid @RequestParam(value = "sale_id" ) Integer id,
                                            @RequestParam(value = "card")   String creditcard,
                                            @RequestHeader(value = "Authorization") HttpHeaders headers,
                                            @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(RestResponse.of(service.buyTheProductInAd(id,creditcard,headers,locale)));
    }
}
